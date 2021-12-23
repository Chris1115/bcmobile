package com.example.final_mp

import java.io.Serializable

class NewsModel (
    val news_data: List<Data>
)
{
    data class Data(val id: String?, val headline: String?, val content: String?) : Serializable
}
