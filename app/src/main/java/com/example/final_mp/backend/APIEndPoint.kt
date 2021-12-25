package com.example.final_mp.backend

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIEndPoint {
    @FormUrlEncoded
    @POST("user_data.php")
    fun user_data(
        @Field("username") username : String
    ) : Call<UserModel>

    @GET("forum_data.php")
    fun forum_data() : Call<ForumModel>

    @GET("news_data.php")
    fun news_data() : Call<NewsModel>

    @GET("logout.php")
    fun logout() : Call<LogoutModel>

    @FormUrlEncoded
    @POST("login.php")
    fun login(
        @Field("username") username : String,
        @Field("password") password : String
    ) : Call<LoginModel>
}