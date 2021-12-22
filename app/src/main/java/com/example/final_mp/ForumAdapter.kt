package com.example.final_mp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ForumAdapter(
    val forums: ArrayList<ForumModel.Data>,
    val listener: onAdapterListener
    ): RecyclerView.Adapter<ForumAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter, parent, false)
    )

    override fun onBindViewHolder(holder: ForumAdapter.ViewHolder, position: Int) {
        val content = forums[position]

        val title = content.title
        holder.text_title.text = title
        holder.itemView.setOnClickListener{
            listener.onClick( content )
        }
    }

    override fun getItemCount() = forums.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val text_title = view.findViewById<TextView>(R.id.title)
        val img_del = view.findViewById<ImageView>(R.id.image_delete)
    }

    public fun setData(data: List<ForumModel.Data>)
    {
        forums.clear()
        forums.addAll(data)
        notifyDataSetChanged()
    }

    interface onAdapterListener
    {
        fun onClick(data: ForumModel.Data)
    }
}