package com.justin.justmovie

import retrofit2.Response

object ApiRepository {

    suspend fun getMovieDetail(movieId:String):Response<MovieDetailModel> {
        return RetrofitManager.apiService.getMovieDetail("")
    }

}