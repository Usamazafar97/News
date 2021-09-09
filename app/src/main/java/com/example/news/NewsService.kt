package com.example.news

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

//https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=8ffd1544a4674b3e82eb3813b0c11ff9


public const val API_KEY = "8ffd1544a4674b3e82eb3813b0c11ff9"
public const val BASE_URL = "https://newsapi.org/"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadline(@Query("country") country: String, @Query("page") page: Int): Call<News>
}

object NewsService{
    val newsInstance:NewsInterface

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}