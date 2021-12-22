package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Forum : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var adapter: ForumAdapter
    private lateinit var listdata: RecyclerView
    private lateinit var button: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum)
        setupView()
        setupList()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getNote()
    }

    private fun setupView()
    {
        listdata = findViewById(R.id.list_data)
        button = findViewById(R.id.fab_create)
    }
    private fun setupList()
    {
        adapter = ForumAdapter(arrayListOf(), object : ForumAdapter.onAdapterListener{
            override fun onClick(data: ForumModel.Data) {
                startActivity(Intent(this@Forum, Forum_detail::class.java)
                    .putExtra("title", data)
                )
            }
        })
        listdata.adapter = adapter
    }

    private fun setupListener()
    {
        button.setOnClickListener{
            startActivity(Intent(this, Dashboard::class.java))
        }
    }

    private fun getNote()
    {
        super.onStart()

        API.forum_data().enqueue(object: Callback<ForumModel>{
            override fun onResponse(call: Call<ForumModel>, response: Response<ForumModel>) {
                if(response.isSuccessful)
                {
                    val result = response.body()!!.forums_data
                    adapter.setData(result)
                }
            }

            override fun onFailure(call: Call<ForumModel>, t: Throwable) {
                Log.e("", t.toString())
            }

        } )

    }
}