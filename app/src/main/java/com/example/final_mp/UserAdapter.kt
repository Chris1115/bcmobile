package com.example.final_mp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserAdapter(
    val users: ArrayList<UserModel.Data>
    ): RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= ViewHolder (
        LayoutInflater.from(parent.context)
            .inflate(R.layout.adapter, parent, false)
    )

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        val user = users[position]

        val id = user.id
        val name = user.name
        val username = user.username
        val password = user.password
        holder.text_view.text = id+name+username+password
    }

    override fun getItemCount() = users.size

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val text_view = view.findViewById<TextView>(R.id.text)
        val img_del = view.findViewById<ImageView>(R.id.image_delete)
    }

    public fun setData(data: List<UserModel.Data>)
    {
        users.clear()
        users.addAll(data)
        notifyDataSetChanged()
    }
}