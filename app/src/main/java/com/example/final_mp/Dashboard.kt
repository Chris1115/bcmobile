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
import kotlin.math.log

class Dashboard : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private val username by lazy { intent.getStringExtra("username") }
    private lateinit var forumbtn: MaterialButton
    private lateinit var newsbtn: MaterialButton
    private lateinit var profilebtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        setupView()
        setupListener()
    }

    private fun setupView()
    {
        forumbtn = findViewById(R.id.forumbtn)
        newsbtn = findViewById(R.id.newsbtn)
        profilebtn = findViewById(R.id.profilebtn)
    }

    private fun setupListener()
    {
        forumbtn.setOnClickListener{
            startActivity(Intent(this, Forum::class.java))
        }

        newsbtn.setOnClickListener{
            startActivity(Intent(this, News::class.java))
        }

        profilebtn.setOnClickListener {
            startActivity(Intent(this, Profile::class.java)
                .putExtra("username", username)
            )
        }
    }
}