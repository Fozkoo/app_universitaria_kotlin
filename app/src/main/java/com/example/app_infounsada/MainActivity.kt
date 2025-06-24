package com.example.app_infounsada

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_infounsada.data.NewsDTO
import com.example.app_infounsada.databinding.ActivityMainBinding
import com.example.app_infounsada.network.RetrofitClient
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    private lateinit var newsAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadTopicButtons()
        setupNewsRecycler()
        loadNoticias()
    }

    private fun setupNewsRecycler() {
        newsAdapter = NewsAdapter(emptyList())
        binding.recyclerNoticias.layoutManager = LinearLayoutManager(this)
        binding.recyclerNoticias.adapter = newsAdapter
    }



    private fun loadNoticias() {
        coroutineScope.launch {
            try {
                val noticias: List<NewsDTO> = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getAllNews()
                }
                newsAdapter.updateData(noticias)
            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Error al cargar noticias", Toast.LENGTH_SHORT).show()
                Log.e("NEWS_LOAD_ERROR", "Error al cargar noticias", e)
            }
        }
    }


    private fun loadTopicButtons() {
        coroutineScope.launch {
            try {
                val topics = withContext(Dispatchers.IO) {
                    RetrofitClient.instance.getAllTopics()
                }

                binding.buttonsContainer.removeAllViews()

                // Botones dinámicos
                topics.forEach { topic ->
                    val nombre = topic.name.trim()
                    binding.buttonsContainer.addView(createButton(nombre) {
                        val intent = Intent(this@MainActivity, TopicModulesActivity::class.java)
                        intent.putExtra("topic_name", nombre)
                        startActivity(intent)
                    })
                }

                showToast("Tópicos cargados: ${topics.size}")
            } catch (e: Exception) {
                Log.e("TOPIC_LOAD_ERROR", "Error al obtener tópicos", e)
                showToast("No se pudieron cargar los tópicos")
            }
        }
    }

    private fun createButton(text: String, onClick: () -> Unit): Button {
        return Button(this).apply {
            this.text = text
            layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {
                setMargins(0, 0, 0, 16.dpToPx())
            }
            setBackgroundColor(getColor(android.R.color.holo_blue_dark))
            setTextColor(getColor(android.R.color.white))
            setOnClickListener { onClick() }
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun Int.dpToPx(): Int = (this * resources.displayMetrics.density).toInt()
}
