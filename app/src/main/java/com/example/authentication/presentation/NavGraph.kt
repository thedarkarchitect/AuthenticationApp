package com.example.authentication.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication.presentation.home.Home
import com.example.authentication.presentation.login.LoginPage
import com.example.authentication.presentation.registration.SignupPage
import com.example.authentication.utils.Screen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.Login.route
    ){
        composable(
            route = Screen.Login.route
        ){
            LoginPage(navController = navController)
        }
        composable(
            route = Screen.SignIn.route
        ){
            SignupPage(navController = navController)
        }
        composable(
            route = Screen.Home.route
        ){
            Home(navController = navController)
        }
    }
}