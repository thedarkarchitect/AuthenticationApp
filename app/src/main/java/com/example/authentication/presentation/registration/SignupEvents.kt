package com.example.authentication.presentation.registration

sealed class SignupEvents {
    data class UsernameChanged(val userName: String): SignupEvents()
    data class EmailChanged(val email: String): SignupEvents()
    data class PasswordChanged(val password: String): SignupEvents()

//    data object Submit: SignupEvents()
}