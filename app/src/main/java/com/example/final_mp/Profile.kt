package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Profile : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private val username_data by lazy { intent.getStringExtra("username").toString()}
    private lateinit var adapter : UserAdapter
    private lateinit var text_name : TextView
    private lateinit var text_username : TextView
    private lateinit var logoutbtn : MaterialButton

    enum class user_data{
        Id, names, username, password
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        setupView()
        setupListener()
    }

    override fun onStart() {
        super.onStart()
        getData()
    }

    private fun setupView()
    {
        text_name = findViewById(R.id.text_name)
        text_username = findViewById(R.id.text_username)
        logoutbtn = findViewById(R.id.logoutbtn)
    }

    private fun setupListener()
    {
        logoutbtn.setOnClickListener {
            API.logout().enqueue(object: Callback<LogoutModel>{
                override fun onResponse(call: Call<LogoutModel>, response: Response<LogoutModel>) {
                    if (response.isSuccessful)
                    {
                        val result = response.body()

                        if (result!!.message == "Login Success") {
                            Toast.makeText(
                                applicationContext,
                                result!!.message,
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        else
                        {
                            Toast.makeText(
                                applicationContext,
                                result!!.message
                                , Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                    else
                    {
                        Log.e("Profile", "Logout Response Failed")
                    }
                }

                override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                    Log.e("Profile", t.message.toString())
                }

            })

            startActivity(Intent(this, MainActivity::class.java))

        }
    }

    private fun getData()
    {
        super.onStart()

        API.user_data(username_data).enqueue(object: Callback<UserModel>{
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                if (response.isSuccessful)
                {
                    Log.d("Profile", response.toString())
                    val result = response.body()!!.users_data
                    result.forEach {
                        text_name.text = it.name
                        text_username.text = it.username
                    }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                print(t.toString())
            }
        })
    }
}