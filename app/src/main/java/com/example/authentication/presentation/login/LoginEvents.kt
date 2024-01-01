package com.example.authentication.presentation.login

sealed class LoginEvents {
    data class EmailChanged(val email: String): LoginEvents()
    data class PasswordChanged(val password: String): LoginEvents()

//    data object Submit: LoginEvents()
}