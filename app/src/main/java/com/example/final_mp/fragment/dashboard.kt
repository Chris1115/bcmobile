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
import com.example.final_mp.backend.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class dashboard : Fragment() {

    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var adapter: ForumAdapter
    private lateinit var listdata: RecyclerView
    private lateinit var adapter2: NewsAdapter
    private lateinit var listdata2: RecyclerView

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        startLink()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.dashboard, container, false)
        val activity = view!!.context as AppCompatActivity
        val forum_detail = forum_detail()
        val news_detail = news_detail()

        listdata = view.findViewById(R.id.list_data)

        adapter = ForumAdapter(arrayListOf(), object : ForumAdapter.onAdapterListener{
            override fun onClick(data: ForumModel.Data) {
                val bundle = Bundle()
                bundle.putSerializable("title", data)

                forum_detail.arguments = bundle
                activity.supportFragmentManager.beginTransaction()
                    .apply {
                        replace(R.id.fram_nav, forum_detail, forum_detail::class.java.simpleName)
                            .commit()
                    }
            }
        })

        listdata.adapter = adapter

        listdata2 = view.findViewById(R.id.list_data2)

        adapter2 = NewsAdapter(arrayListOf(), object : NewsAdapter.onAdapterListener{
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

        listdata2.adapter = adapter2


        return view
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
            dashboard().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun startLink()
    {
        super.onStart()
        getNews()
        getForum()
    }

    private fun getForum()
    {
        API.forum_data().enqueue(object: Callback<ForumModel> {
            override fun onResponse(call: Call<ForumModel>, response: Response<ForumModel>) {
                if(response.isSuccessful)
                {
                    Log.e("Forum", response.toString())
                    val result = response.body()!!.forums_data
                    adapter.setData(result)

                    result.forEach {
                        Log.e("Forum", it.title.toString())
                    }
                }
            }

            override fun onFailure(call: Call<ForumModel>, t: Throwable) {
                Log.e("", t.toString())
            }

        } )

    }

    private fun getNews()
    {
        API.news_data().enqueue(object: Callback<NewsModel> {
            override fun onResponse(call: Call<NewsModel>, response: Response<NewsModel>) {
                if(response.isSuccessful)
                {
                    Log.e("News", response.toString())
                    val result = response.body()!!.news_data
                    adapter2.setData(result)

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