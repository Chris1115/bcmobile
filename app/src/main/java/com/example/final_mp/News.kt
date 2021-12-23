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

class News : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var adapter: NewsAdapter
    private lateinit var listdata: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)
        setupView()
        setupList()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getForum()
    }

    private fun setupView()
    {
        listdata = findViewById(R.id.list_data)
    }
    private fun setupList()
    {
        adapter = NewsAdapter(arrayListOf(), object : NewsAdapter.onAdapterListener{
            override fun onClick(data: NewsModel.Data) {
                startActivity(Intent(this@News, News_detail::class.java)
                    .putExtra("content", data)
                )
            }
        })
        listdata.adapter = adapter
    }

    private fun setupListener()
    {

    }

    private fun getForum()
    {
        super.onStart()

        API.news_data().enqueue(object: Callback<NewsModel>{
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if(response.isSuccessful)
                {
                    val result = response.body()!!.news_data
                    adapter.setData(result)
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("", t.toString())
            }

        } )

    }
}