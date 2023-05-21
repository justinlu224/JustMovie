package com.justin.justmovie

import android.util.Log
import com.justin.justmovie.model.LoginResponse
import kotlinx.coroutines.flow.flow
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiRepository @Inject constructor(
    private val loginService: LoginService,
    private val apiService: ApiService
    ) {

    suspend fun getMovieDetail(movieId:String):Response<MovieDetailModel> {
        return apiService.getMovieDetail("")
    }


    fun getLogin(errorCode:String) = flow {
        Log.d("Home","ApiRepository ${Thread.currentThread().name}")
        if (errorCode.isBlank()) emit(loginService.loginFlow())
        else emit(loginService.loginFlow(errorCode))

    }

   suspend fun getLogin2(errorCode: String): Response<LoginResponse> {
       return if (errorCode.isBlank()) loginService.login()
        else loginService.login(errorCode)
    }


}