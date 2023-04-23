package com.justin.justmovie.model

data class LoginRequest(
    val email: String,
    val password: String
)

data class LoginResponse(
    val member: Member?,
    val token: Token?
)

data class Member(
    val id: Int,
    val email: String?,
    val phone_number: String?,
    val is_first_time_login: Boolean?,
    val nickname: String?,
    val carrier: Carrier?
) {

    data class Carrier(
        val id: Int,
        val barcode: String?,
        val email: String?,
        val phone_number: String?,
        val is_email_verified: Boolean?
    )
}

data class Token(
    val access_token: String?
)

data class ErrorResponse(
    val code: String?,
    val message: String?
)