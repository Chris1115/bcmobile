package com.example.final_mp.fragment

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.final_mp.MainActivity
import com.example.final_mp.R
import com.example.final_mp.backend.APIRetrofit
import com.example.final_mp.backend.ForumModel
import com.example.final_mp.backend.LogoutModel
import com.example.final_mp.backend.UserModel
import com.example.final_mp.helper.session
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class profile : Fragment() {
    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var text_name : TextView
    private lateinit var text_username : TextView
    private lateinit var logoutbtn : MaterialButton
    private lateinit var session : session
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)
        val activity = view!!.context as AppCompatActivity
//        val intent = Intent(this, MainActivity::class.java)

        text_name = view.findViewById(R.id.text_name)
        logoutbtn = view.findViewById(R.id.logoutbtn)

        logoutbtn.setOnClickListener {
            API.logout().enqueue(object: Callback<LogoutModel>{
                override fun onResponse(call: Call<LogoutModel>, response: Response<LogoutModel>) {

                    if (response.isSuccessful)
                    {
                        val result = response.body()

                        if (result!!.message == "Logout Success") {
//                            startActivity(intent)
                        }
                        else
                        {

                        }
                    }
                    else
                    {

                    }
                }

                override fun onFailure(call: Call<LogoutModel>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        }


        val username_data = arguments?.get("username")
        Log.e("Profile", username_data.toString())

        getData(username_data.toString())

        return view
    }


    companion object {
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getData(username: String)
    {
        API.user_data(username).enqueue(object: Callback<UserModel> {
            override fun onResponse(
                call: Call<UserModel>,
                response: Response<UserModel>
            ) {
                if (response.isSuccessful)
                {
                    Log.e("Profile", response.toString())
                    val result = response.body()!!.users_data
                    result.forEach {
                        Log.e("Profile", it.name.toString())
//                        text_name.text = it.name
                    }
                }
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                print(t.toString())
            }
        })
    }
}
