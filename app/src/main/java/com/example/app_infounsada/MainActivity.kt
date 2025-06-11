package com.example.app_infounsada

import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.app_infounsada.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        generateButtons()
    }


    private fun getRetrofit():Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://urlAPI/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    private fun searchByName(query:String) {
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(APIService::class.java).getAllModulesByID()
        }
    }

    private fun generateButtons() {
        val buttonsTexts = arrayListOf("Calendario Academico", "Becas", "Plataformas", "Tutorias")

        val buttonsContainer: LinearLayout = binding.buttonsContainer

        buttonsContainer.removeAllViews()

        for (text in buttonsTexts) {
            val button = Button(this)
            button.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            ).apply {

            }
            button.text = text
            button.setOnClickListener {
                Toast.makeText(this, "$text clickeado!", Toast.LENGTH_SHORT).show()
            }
            buttonsContainer.addView(button)
        }
    }
}