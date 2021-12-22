package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Dashboard : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var forumbtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupView()
        setupListener()
    }

    private fun setupView()
    {
        forumbtn = findViewById(R.id.forumbtn)
    }

    private fun setupListener()
    {
        forumbtn.setOnClickListener{
            startActivity(Intent(this, Forum::class.java))
        }
    }
}