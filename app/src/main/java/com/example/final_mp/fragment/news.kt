package com.example.final_mp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.final_mp.R
import com.example.final_mp.backend.APIRetrofit
import com.example.final_mp.backend.NewsAdapter
import com.example.final_mp.backend.NewsModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class news: Fragment() {
    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var adapter: NewsAdapter
    private lateinit var listdata: RecyclerView
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        getNews()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.news, container, false)
        val activity = view!!.context as AppCompatActivity
        val news_detail = news_detail()

        listdata = view.findViewById(R.id.list_data)

        adapter = NewsAdapter(arrayListOf(), object : NewsAdapter.onAdapterListener{
            override fun onClick(data: NewsModel.Data) {
                val bundle = Bundle()
                bundle.putSerializable("title", data)

                news_detail.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .apply {
                        replace(R.id.fram_nav, news_detail, news_detail::class.java.simpleName)
                            .commit()
                    }
            }
        })

        listdata.adapter = adapter

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            forum().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }


    private fun getNews()
    {
        super.onStart()

        API.news_data().enqueue(object: Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if(response.isSuccessful)
                {
                    Log.e("News", response.toString())
                    val result = response.body()!!.news_data
                    adapter.setData(result)

                    result.forEach {
                        Log.e("News", it.headline.toString())
                    }
                }
            }

            override fun onFailure(call: Call<NewsModel>, t: Throwable) {
                Log.e("", t.toString())
            }

        } )

    }
}