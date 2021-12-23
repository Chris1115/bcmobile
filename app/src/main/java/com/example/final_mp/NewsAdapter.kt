package com.example.final_mp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NewsAdapter(
    val news: ArrayList<NewsModel.Data>,
    val listener: onAdapterListener
): RecyclerView.Adapter<NewsAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter, parent, false)
    )

    override fun onBindViewHolder(holder: NewsAdapter.ViewHolder, position: Int) {
        val content = news[position]

        val headline = content.headline
        holder.text_title.text = headline
        holder.itemView.setOnClickListener{
            listener.onClick( content )
        }
    }

    override fun getItemCount() = news.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val text_title = view.findViewById<TextView>(R.id.title)
    }

    public fun setData(data: List<NewsModel.Data>)
    {
        news.clear()
        news.addAll(data)
        notifyDataSetChanged()
    }

    interface onAdapterListener
    {
        fun onClick(data: NewsModel.Data)
    }
}