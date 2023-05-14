package com.justin.justmovie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justin.justmovie.JustMovieExtansion.data
import com.justin.justmovie.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(val apiRepository: ApiRepository) : ViewModel() {

    private val _loginApiresult = MutableLiveData<ResultStatus<LoginResponse>>()

    val loginApiResult = _loginApiresult

    fun loginWithFlow(errorCode: String?){

        apiRepository.getLogin(errorCode ?: "")
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