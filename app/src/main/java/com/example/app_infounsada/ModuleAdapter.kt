package com.example.app_infounsada

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_infounsada.data.ModuleDTO

class ModuleAdapter(private var originalList: List<ModuleDTO>) :
    RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    private var filteredList: List<ModuleDTO> = originalList

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvModuleName: TextView = itemView.findViewById(R.id.tvModuleName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_module, parent, false)
        return ModuleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = filteredList[position]
        holder.tvModuleName.text = module.name
    }

    override fun getItemCount(): Int = filteredList.size

    fun updateData(newList: List<ModuleDTO>) {
        originalList = newList
        filteredList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        filteredList = if (query.isBlank()) {
            originalList
        } else {
            originalList.filter {
                it.name.contains(query, ignoreCase = true)
            }
        }
        notifyDataSetChanged()
    }
}
