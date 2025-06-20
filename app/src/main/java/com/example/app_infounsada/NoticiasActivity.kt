package com.example.app_infounsada

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_infounsada.databinding.ActivityNoticiasBinding
import com.example.app_infounsada.network.RetrofitClient
import kotlinx.coroutines.*

class NoticiasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNoticiasBinding
    private lateinit var adapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoticiasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = NewsAdapter(emptyList())
        binding.recyclerNoticias.layoutManager = LinearLayoutManager(this)
        binding.recyclerNoticias.adapter = adapter

        cargarNoticias()
    }

    private fun cargarNoticias() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val noticias = RetrofitClient.instance.getAllNews()
                withContext(Dispatchers.Main) {
                    adapter.updateData(noticias)
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@NoticiasActivity, "Error al cargar noticias", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
