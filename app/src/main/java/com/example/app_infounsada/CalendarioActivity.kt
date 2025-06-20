package com.example.app_infounsada

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app_infounsada.data.CalendarEventDTO
import com.example.app_infounsada.databinding.ActivityCalendarioBinding
import com.example.app_infounsada.network.RetrofitClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CalendarioActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarioBinding
    private lateinit var adapter: CalendarEventAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CalendarEventAdapter(emptyList())
        binding.recyclerCalendar.layoutManager = LinearLayoutManager(this)
        binding.recyclerCalendar.adapter = adapter

        fetchCalendarEvents()
    }

    private fun fetchCalendarEvents() {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val events = RetrofitClient.instance.getAllCalendarEvents()
                withContext(Dispatchers.Main) {
                    Log.d("API", "Eventos recibidos: ${events.size}")
                    adapter.updateData(events)
                    Toast.makeText(
                        this@CalendarioActivity,
                        "Eventos cargados: ${events.size}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    Log.e("API_CALENDAR_FAIL", "Excepción: ${e.localizedMessage}", e)
                    Toast.makeText(
                        this@CalendarioActivity,
                        "Error: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
