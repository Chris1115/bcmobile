package com.example.final_mp

import java.io.Serializable

class UserModel (
    val users_data: List<Data>
) {
    data class Data(val id: String?, val name: String?, val username: String?, val password: String?) : Serializable
}