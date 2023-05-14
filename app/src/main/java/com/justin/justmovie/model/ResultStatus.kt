package com.justin.justmovie.model

sealed interface ResultStatus<T>

data class Success<T>(val data:T):ResultStatus<T>
class onLoading<T>:ResultStatus<T>
data class Error<T>(val errorResponse:ErrorResponse):ResultStatus<T>