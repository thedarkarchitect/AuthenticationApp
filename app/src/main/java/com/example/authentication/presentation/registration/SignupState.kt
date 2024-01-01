package com.example.authentication.presentation.registration

import androidx.compose.runtime.MutableState
import com.example.authentication.data.model.UserState

data class SignupState(
    val userName: String = "",
    val email: String =  "",
    val emailError: String? = null,
    val password: String =  "",
    val passwordError: String? = null,
    val userState: UserState = UserState.Loading
)
