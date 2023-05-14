package com.justin.justmovie

import com.google.gson.Gson
import com.justin.justmovie.model.ErrorResponse
import okhttp3.ResponseBody

object JustMovieExtansion {

    fun ResponseBody?.data() = Gson().fromJson(this?.string(), ErrorResponse::class.java)
}
