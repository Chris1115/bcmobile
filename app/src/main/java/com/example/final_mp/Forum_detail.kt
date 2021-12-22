package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class Forum_detail : AppCompatActivity() {
    private val API by lazy { APIRetrofit().endpoint }
    private val forum_data by lazy { intent.getSerializableExtra("title") as ForumModel.Data}
    private lateinit var title: TextView
    private lateinit var question: TextView
    private lateinit var backbtn: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forum_detail)

        setupView()
        setupListener()
    }

    private fun setupView()
    {
        title = findViewById(R.id.title)
        question = findViewById(R.id.question)
        backbtn = findViewById(R.id.backbtn)

        title.setText(forum_data.title)
        question.setText(forum_data.question)
    }

    private fun setupListener()
    {
        backbtn.setOnClickListener{
            startActivity(Intent(this, Forum::class.java))
        }
    }
}