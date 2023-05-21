package com.justin.justmovie

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.justin.justmovie.JustMovieExtansion.data
import com.justin.justmovie.model.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
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


    fun loginWithFlowd(errorCode: String?, dispatcher: CoroutineDispatcher = Dispatchers.IO){

        apiRepository.getLogin(errorCode ?: "")
            .flowOn(dispatcher)
            .onStart { _loginApiresult.postValue( onLoading() )}
            .onEach {
//                Log.d("Home","${Thread.currentThread().name}")
                if (it.isSuccessful) {
                    _loginApiresult.postValue(Success(it.body()!!))
                }else {
                    _loginApiresult.postValue(Error(it.errorBody().data()))
                }
            }
            .launchIn(viewModelScope)
    }

    fun login2(errorCode: String?, dispatcher: CoroutineDispatcher = Dispatchers.IO) {
        _loginApiresult.postValue( onLoading())

        viewModelScope.launch(dispatcher) {

           val result = apiRepository.getLogin2(errorCode ?: "")

            if (result.isSuccessful) {
                _loginApiresult.postValue(Success(result.body()!!))
            }else {
                _loginApiresult.postValue(Error(result.errorBody().data()))
            }

        }


    }

}