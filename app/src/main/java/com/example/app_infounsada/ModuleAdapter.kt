package com.example.app_infounsada

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_infounsada.data.ModuleDTO
import com.squareup.picasso.Picasso

class ModuleAdapter(private var moduleList: List<ModuleDTO>) :
    RecyclerView.Adapter<ModuleAdapter.ModuleViewHolder>() {

    inner class ModuleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtModuleTitle)
        val txtDescription: TextView = itemView.findViewById(R.id.txtModuleDescription)
        val imgModule: ImageView = itemView.findViewById(R.id.imgModule)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModuleViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_module, parent, false)
        return ModuleViewHolder(view)
    }

    override fun getItemCount(): Int = moduleList.size

    override fun onBindViewHolder(holder: ModuleViewHolder, position: Int) {
        val module = moduleList[position]
        holder.txtTitle.text = module.title
        holder.txtDescription.text = module.content

        if (!module.image_path.isNullOrBlank()) {
            holder.imgModule.visibility = View.VISIBLE
            Picasso.get().load(module.image_path).into(holder.imgModule)
        } else {
            holder.imgModule.visibility = View.GONE
        }
    }

    fun updateData(newList: List<ModuleDTO>) {
        moduleList = newList
        notifyDataSetChanged()
    }

    fun filter(query: String) {
        val filtered = moduleList.filter {
            it.title.contains(query, ignoreCase = true) ||
                    it.content.contains(query, ignoreCase = true)
        }
        updateData(filtered)
    }
}
