package com.example.app_infounsada

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.app_infounsada.data.NewsDTO
import com.squareup.picasso.Picasso

class NewsAdapter(private var newsList: List<NewsDTO>) :
    RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    inner class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtTitle: TextView = itemView.findViewById(R.id.txtNewsTitle)
        val txtDescription: TextView = itemView.findViewById(R.id.txtNewsDescription)
        val imgNews: ImageView = itemView.findViewById(R.id.imgNews)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(view)
    }

    override fun getItemCount(): Int = newsList.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = newsList[position]

        holder.txtTitle.text = news.title
        holder.txtDescription.text = news.content

        if (!news.image_path.isNullOrBlank()) {
            holder.imgNews.visibility = View.VISIBLE
            Picasso.get()
                .load(news.image_path)
                .placeholder(android.R.drawable.ic_menu_report_image)
                .error(android.R.drawable.ic_delete)
                .into(holder.imgNews)
        } else {
            holder.imgNews.visibility = View.GONE
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<NewsDTO>) {
        newsList = newList
        notifyDataSetChanged()
    }
}
