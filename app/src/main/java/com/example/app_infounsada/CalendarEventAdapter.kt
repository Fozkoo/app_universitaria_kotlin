package com.example.app_infounsada

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_infounsada.data.CalendarEventDTO

class CalendarEventAdapter(private var events: List<CalendarEventDTO>) :
    RecyclerView.Adapter<CalendarEventAdapter.EventViewHolder>() {

    inner class EventViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.tvEventTitle)
        val tvDescription: TextView = itemView.findViewById(R.id.tvEventDescription)
        val tvDate: TextView = itemView.findViewById(R.id.tvEventDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_event, parent, false)
        return EventViewHolder(view)
    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {
        val event = events[position]
        holder.tvTitle.text = event.title
        holder.tvDescription.text = event.description
        holder.tvDate.text = event.date ?: "Sin fecha"
    }

    override fun getItemCount(): Int = events.size

    fun updateData(newEvents: List<CalendarEventDTO>) {
        events = newEvents
        notifyDataSetChanged()
    }
}
