package com.justin.justmovie

import android.util.Log
import kotlinx.coroutines.flow.flow
import retrofit2.Response

object ApiRepository {

    suspend fun getMovieDetail(movieId:String):Response<MovieDetailModel> {
        return RetrofitManager.apiService.getMovieDetail("")
    }


    fun getLogin(errorCode:String) = flow {
        Log.d("Home","ApiRepository ${Thread.currentThread().name}")
        if (errorCode.isBlank()) emit(RetrofitManager.loginService.loginFlow())
        else emit(RetrofitManager.loginService.loginFlow(errorCode))

    }


}