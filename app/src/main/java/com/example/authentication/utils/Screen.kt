package com.example.authentication.utils

sealed class Screen(val route: String) {
    data object Login: Screen("Login")
    data object SignIn: Screen("Sign In")
    data object Home: Screen("Home")
}