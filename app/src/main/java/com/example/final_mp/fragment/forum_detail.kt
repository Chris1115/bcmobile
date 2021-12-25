package com.example.final_mp.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.final_mp.R
import com.example.final_mp.backend.ForumModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class forum_detail: Fragment() {
    private lateinit var text_title : TextView
    private lateinit var text_question : TextView
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
        val view = inflater.inflate(R.layout.activity_forum_detail, container, false)

        val args = this.arguments
        val data = args?.getSerializable("title") as ForumModel.Data

        if (data != null)
        {
            text_title = view.findViewById(R.id.title)
            text_question = view.findViewById(R.id.question)

            text_title.text = data.title
            text_question.text = data.question
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