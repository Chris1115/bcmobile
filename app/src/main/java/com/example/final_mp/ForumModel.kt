package com.example.final_mp

import java.io.Serializable

class ForumModel (
    val forums_data: List<Data>
)
{
    data class Data(val id: String?, val title: String?, val question: String?) : Serializable
}
