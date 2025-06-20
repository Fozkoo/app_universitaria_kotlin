package com.example.app_infounsada

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_infounsada.databinding.ActivityTopicModulesBinding
import com.example.app_infounsada.data.ModuleDTO
import com.example.app_infounsada.network.RetrofitClient
import kotlinx.coroutines.*

class TopicModulesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTopicModulesBinding
    private lateinit var adapter: ModuleAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicModulesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val topicName = intent.getStringExtra("topic_name") ?: "Tópico"
        title = topicName.uppercase()

        // Cambiar fondo según el tópico
        when (topicName.lowercase()) {
            "becas", "extensiones", "examenes finales" -> {
                binding.root.setBackgroundColor(Color.parseColor("#cfbb75")) // dorado claro
            }
            else -> {
                binding.root.setBackgroundColor(Color.WHITE)
            }
        }

        // Configurar RecyclerView
        adapter = ModuleAdapter(emptyList())
        binding.recyclerModules.layoutManager = LinearLayoutManager(this)
        binding.recyclerModules.adapter = adapter

        // Cargar módulos desde la API
        cargarModulosPorTópico(topicName)
    }

    private fun cargarModulosPorTópico(topic: String) {
        coroutineScope.launch {
            try {
                val modules: List<ModuleDTO> = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getModulesByTopic(topic)
                }
                adapter.updateData(modules)
            } catch (e: Exception) {
                Toast.makeText(this@TopicModulesActivity, "Error al cargar módulos", Toast.LENGTH_LONG).show()
            }
        }
    }
}
