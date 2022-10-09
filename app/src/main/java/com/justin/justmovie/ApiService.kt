package com.justin.justmovie

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("3/movie/{movie_id}")
    suspend fun getMovieDetail(@Path("movie_id") movieId:String,@Query("api_key") apiKey:String = "9f19546618669bc8c0ef50afa6d07a48",@Query("language") language:String = "zh-tw"): Response<MovieDetailModel>

    @GET("3/movie/now_playing")
    suspend fun getNowPlaying(@Query("api_key") apiKey:String = "9f19546618669bc8c0ef50afa6d07a48",@Query("language") language:String = "zh-tw")



}
