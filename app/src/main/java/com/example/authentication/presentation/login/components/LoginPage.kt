package com.example.authentication.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.authentication.presentation.components.Annote
import com.example.authentication.presentation.components.CardItem
import com.example.authentication.presentation.components.TopHeading
import com.example.authentication.presentation.login.LoginEvents
import com.example.authentication.presentation.login.LoginViewModel
import com.example.authentication.presentation.registration.SignupEvents
import com.example.authentication.ui.theme.AuthenticationTheme
import com.example.authentication.ui.theme.CustomBlue
import com.example.authentication.ui.theme.CustomBlue1
import com.example.authentication.ui.theme.CustomBlue2
import com.example.authentication.utils.Screen


@Composable
fun LoginPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginVm: LoginViewModel
) {

    val state = loginVm.loginState.value
    val onEvent = loginVm::onEvent
    val context = LocalContext.current

//    Surface{
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        listOf(
                            CustomBlue1, CustomBlue2, CustomBlue
                        )
                    )
                )
        ) {
            Column(
                modifier = modifier
                    .padding(25.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier.fillMaxWidth()
                ) {
                    TopHeading(
                        modifier = modifier.padding(bottom = 32.dp),
                        heading = "Log in",
                        navController = navController
                    )
                }
                CardItem(
                    buttonText = "Log in",
                    buttonClicked = {
                        //viewmodel method for action
                        loginVm.login(
                            context,
                            userEmail = state.email,
                            userPassword = state.password
                        )
                        //navigate to home screen
                        navController.navigate(
                            Screen.Home.route
                        )
                    },
                    emailValue = state.email,
                    onEmailChangeValue = { onEvent(LoginEvents.EmailChanged(it)) },
                    password = state.password,
                    onPasswordChangeValue = { onEvent(LoginEvents.PasswordChanged(it)) },
                    userName = "",
                    onUserNameChangeValue = {},
                )

                Annote(
                    direction = "Don't have an Account?",
                    nextPage = "SignUp",
                    textClicked = {
                        navController.navigate(
                            Screen.SignIn.route
                        )
                    }
                )
            }
        }
//    }
}


//@Preview(showBackground = true)
//@Composable
//fun LoginPagePreview() {
//    AuthenticationTheme {
//        LoginPage(
//            navController = rememberNavController(),
//            loginVm = hiltViewModel()
//        )
//    }
//}