package com.justin.justmovie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.justin.justmovie.RetrofitManager.data
import com.justin.justmovie.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.concurrent.Flow

class HomeViewModel : ViewModel() {

    private val _loginApiresult = MutableLiveData<ResultStatus<LoginResponse>>()

    val loginApiResult = _loginApiresult

    fun loginWithFlow(errorCode: String?){

        ApiRepository.getLogin(errorCode ?: "")
            .flowOn(Dispatchers.IO)
            .onStart { _loginApiresult.postValue( onLoading() )}
            .onEach {
                Log.d("Home","${Thread.currentThread().name}")
                if (it.isSuccessful) {
                   _loginApiresult.postValue(Success(it.body()!!))
                }else {
                    _loginApiresult.postValue(Error(it.errorBody().data()))
                }
            }
            .launchIn(viewModelScope)
    }

}