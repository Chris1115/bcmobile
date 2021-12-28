package com.example.final_mp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.final_mp.fragment.*
import com.google.android.material.bottomnavigation.BottomNavigationView

class Dashboard : AppCompatActivity() {

    private val username by lazy { intent.getStringExtra("username") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val dashboard = dashboard()
        val news = news()
        val forum = forum()
        val profile = profile()
        val bottom_navigation = findViewById<BottomNavigationView>(R.id.bottom_navigation)

        curr(dashboard, username.toString())


        bottom_navigation.setOnItemSelectedListener { item ->

            when (item.itemId) {
                R.id.dashboard -> curr(dashboard, username.toString())
                R.id.news -> curr(news, username.toString())
                R.id.forum -> curr(forum, username.toString())
                R.id.profile -> curr(profile, username.toString())
            }
            true
        }

        supportActionBar?.hide()
    }

    private fun curr(fragment: Fragment, data : String){
        Log.e("Dashboard", data)
        supportFragmentManager.beginTransaction().apply {
            val bundle = Bundle().apply {
                putString("username", data)
            }
            fragment.arguments = bundle
            replace(R.id.fram_nav, fragment)
                .commit()
        }
    }
}