package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.google.android.material.button.MaterialButton

class News_detail : AppCompatActivity() {
    private val API by lazy { APIRetrofit().endpoint }
    private val news_data by lazy { intent.getSerializableExtra("content") as NewsModel.Data}
    private lateinit var headline: TextView
    private lateinit var content: TextView
    private lateinit var backbtn: MaterialButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_detail)

        setupView()
        setupListener()
    }

    private fun setupView()
    {
        headline = findViewById(R.id.headline)
        content = findViewById(R.id.content)
        backbtn = findViewById(R.id.backbtn)

        headline.setText(news_data.headline)
        content.setText(news_data.content)
    }

    private fun setupListener()
    {
        backbtn.setOnClickListener{
            startActivity(Intent(this, News::class.java))
        }
    }
}