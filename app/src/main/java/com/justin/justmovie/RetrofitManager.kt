package com.justin.justmovie

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object RetrofitManager {

    private const val baseUrl = "https://api.themoviedb.org/"

   private val httpLogging = HttpLoggingInterceptor().apply {
        setLevel(HttpLoggingInterceptor.Level.BODY)
    }

   private val httpClient = OkHttpClient.Builder().apply {
        addInterceptor(httpLogging)
    }

   private val retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val apiService = retrofit.create<ApiService>()

}
