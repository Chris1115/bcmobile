package com.example.final_mp.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.final_mp.R
import com.example.final_mp.backend.APIRetrofit
import com.example.final_mp.backend.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class profile : Fragment() {
    private val API by lazy { APIRetrofit().endpoint }
    private lateinit var text_name : TextView
    private lateinit var text_username : TextView
    private var username_data : String = ""
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

        getData()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.activity_profile, container, false)

        val args = this.arguments
        val data = args?.getString("username")

        if (data != null)
        {
            text_name = view.findViewById(R.id.title)
            text_username = view.findViewById(R.id.question)
        }

        username_data = data.toString()
        return view
    }

    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            profile().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    private fun getData()
    {
        API.user_data(username_data).enqueue(object: Callback<UserModel> {
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
