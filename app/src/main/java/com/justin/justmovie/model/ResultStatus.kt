package com.justin.justmovie.model

sealed interface ResultStatus<T>

data class Success<T>(val data:T):ResultStatus<T>
class onLoading<Noting>:ResultStatus<Noting>
data class Error<Nothing>(val errorResponse:ErrorResponse):ResultStatus<Nothing>