package com.justin.justmovie

import com.justin.justmovie.model.LoginRequest
import com.justin.justmovie.model.LoginResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId:String,@Query("api_key") apiKey:String = "9f19546618669bc8c0ef50afa6d07a48",@Query("language") language:String = "zh-tw"): Response<MovieDetailModel>

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey:String = "9f19546618669bc8c0ef50afa6d07a48",@Query("language") language:String = "zh-tw")

}

interface LoginService {

    @POST("v1/login")
    suspend fun login(@Query("error") error:String? = null): Response<LoginResponse>

    @POST("v1/login")
   suspend fun loginFlow(@Query("error") error:String? = null): Response<LoginResponse>

}
