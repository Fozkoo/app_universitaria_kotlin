package com.example.app_infounsada

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_infounsada.databinding.ActivityMainBinding
import com.example.app_infounsada.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ModuleAdapter
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Inicializar RecyclerView
        adapter = ModuleAdapter(emptyList())
        binding.recyclerModules.layoutManager = LinearLayoutManager(this)
        binding.recyclerModules.adapter = adapter

        // Listener del SearchView
        binding.svModules.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean = false
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter(newText.orEmpty())
                return true
            }
        })

        generateButtons()
        testApiConnection()
    }

    private fun testApiConnection() {
        coroutineScope.launch {
            try {
                val modules = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getAllModules()
                }

                adapter.updateData(modules)

                modules.forEach { module ->
                    Log.d("BACKEND_DATA", """
                        ID: ${module.id}
                        Nombre: ${module.name}
                        Temas: ${module.topicos.size}
                    """.trimIndent())
                }

                showToast("Conexión exitosa (${modules.size} módulos recibidos)")
            } catch (e: Exception) {
                Log.e("API_FAIL", "Fallo la conexión: ${e.javaClass.simpleName} - ${e.message}")
                showToast("Error al conectar con el backend")
            }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun generateButtons() {
        val buttonsTexts = listOf(
            "Calendario Academico",
            "Becas",
            "Plataformas",
            "Tutorias"
        )

        binding.buttonsContainer.apply {
            removeAllViews()
            buttonsTexts.forEach { text ->
                addView(createButton(text))
            }
        }
    }

    private fun createButton(text: String): Button {
        return Button(this).apply {
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16.dpToPx())
            }

            this.text = text
            setOnClickListener { handleButtonClick(text) }
            setBackgroundColor(getColor(android.R.color.holo_blue_dark))
            setTextColor(getColor(android.R.color.white))
        }
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()

    private fun handleButtonClick(buttonText: String) {
        val activityClass = when (buttonText) {
            "Calendario Academico" -> CalendarioActivity::class.java
            "Becas" -> BecasActivity::class.java
            "Plataformas" -> PlataformasActivity::class.java
            "Tutorias" -> TutoriasActivity::class.java
            else -> {
                showToast("$buttonText clickeado!")
                return
            }
        }
        startActivity(Intent(this, activityClass))
    }
}
