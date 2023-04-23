package com.justin.justmovie

import com.google.gson.Gson
import com.justin.justmovie.model.ErrorResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
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



    private val loginRetrofit = Retrofit.Builder()
        .baseUrl("https://fcb10186-f7c3-4b49-be0f-13d2ea7c213f.mock.pstmn.io")
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient.build())
        .build()

    val loginService = loginRetrofit.create(LoginService::class.java)

    fun ResponseBody?.data() = Gson().fromJson(this?.string(), ErrorResponse::class.java)

}
