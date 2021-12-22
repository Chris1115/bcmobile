package com.example.final_mp

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIEndPoint {
    @GET("user_data.php")
    fun user_data() : Call<UserModel>

    @GET("forum_data.php")
    fun forum_data() : Call<ForumModel>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username : String
    ) : Call<LoginModel>
}