package com.example.final_mp.helper

import android.content.Context
import android.util.Log

class session (context: Context) {
    companion object{
        const val session_name = "session"
        const val username = "username"
    }

    val session = context.getSharedPreferences(session_name, Context.MODE_PRIVATE )

    fun setData(username : String){
        val editor = session.edit()
        editor.putString(username, username)
        editor.commit()
    }

    fun getData(): String? {
        val username = session.getString(username, "")
        Log.e("Session", username.toString())
        return username.toString()
    }
}