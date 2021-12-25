package com.example.final_mp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import com.example.final_mp.backend.APIRetrofit
import com.example.final_mp.backend.LoginModel
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var submitBtn: MaterialButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        setupView()
        setupListener()
    }

    private fun setupView()
    {
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        submitBtn = findViewById(R.id.loginbtn)
    }

    private fun setupListener()
    {
        var access: Boolean = false
        submitBtn.setOnClickListener{
            if (username.text.toString().isNotEmpty())
            {
                API.login(username.text.toString(), password.text.toString())
                    .enqueue(object : Callback<LoginModel>{
                        override fun onResponse(call: Call<LoginModel>, response: Response<LoginModel>)
                        {
                            if (response.isSuccessful)
                            {
                                val result = response.body()

                                if (result!!.message == "Login Success") {
                                    Toast.makeText(
                                        applicationContext,
//                                        result!!.message,
                                        result!!.message,
                                        Toast.LENGTH_SHORT
                                    ).show()

                                    access = true
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

                            }
                        }

                        override fun onFailure(call: Call<LoginModel>, t: Throwable) {
                            Log.e("", t.message.toString())
                        }

                    })
            }
            else
            {
                Toast.makeText(
                    applicationContext,
                    "Mohon mengisi username"
                    , Toast.LENGTH_SHORT
                ).show()
            }

            if(access)
            {
                startActivity(Intent(this, Dashboard::class.java)
            .putExtra("username", username.text.toString())
                )
            }
            else
            {
                Log.e("Login", "Login failed")
            }
        }
    }
}