package com.example.authentication.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authentication.presentation.home.Home
import com.example.authentication.presentation.login.LoginViewModel
import com.example.authentication.presentation.login.components.LoginPage
import com.example.authentication.presentation.registration.SignupViewModel
import com.example.authentication.presentation.registration.components.SignupPage
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
            LoginPage(
                navController = navController,
                loginVm = hiltViewModel<LoginViewModel>()
            )
        }
        composable(
            route = Screen.SignIn.route
        ){
            val signupViewmodel = hiltViewModel<SignupViewModel>()
            SignupPage(
                navController = navController,
                viewModel = signupViewmodel
            )
        }
        composable(
            route = Screen.Home.route
        ){
            Home(
                navController = navController,
                loginVm = hiltViewModel<LoginViewModel>()
            )
        }
    }
}