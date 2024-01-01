package com.example.authentication.presentation.login

import com.example.authentication.data.model.UserState

data class LoginState(
    val email: String =  "",
    val emailError: String? = null,
    val password: String =  "",
    val passwordError: String? = null,
    val userState: UserState = UserState.Loading
)
