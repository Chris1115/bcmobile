package com.example.final_mp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.final_mp.R
import com.example.final_mp.backend.NewsModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class news_detail: Fragment() {
    private lateinit var text_headline : TextView
    private lateinit var text_content : TextView
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.activity_news_detail, container, false)

        val args = this.arguments
        val data = args?.getSerializable("title") as NewsModel.Data

        if (data != null)
        {
            text_headline = view.findViewById(R.id.headline)
            text_content = view.findViewById(R.id.content)

            text_headline.text = data.headline
            text_content.text = data.content
        }

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
}