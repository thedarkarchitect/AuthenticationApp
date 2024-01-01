package com.example.authentication.presentation.registration.components

import android.widget.Toast
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.authentication.data.model.UserState
import com.example.authentication.presentation.components.Annote
import com.example.authentication.presentation.components.CardItem
import com.example.authentication.presentation.components.TopHeading
import com.example.authentication.presentation.registration.SignupEvents
import com.example.authentication.presentation.registration.SignupViewModel
import com.example.authentication.ui.theme.CustomBlue
import com.example.authentication.ui.theme.CustomBlue1
import com.example.authentication.ui.theme.CustomBlue2
import com.example.authentication.utils.Screen


@Composable
fun SignupPage(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: SignupViewModel
) {
    val context = LocalContext.current
    val state = viewModel.signState.value
    val onEvent = viewModel::onEvent


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
    ){
        Column(
            modifier = modifier
                .padding(25.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = modifier.fillMaxWidth(),
                contentAlignment = Alignment.CenterStart
            ){
                TopHeading(
                    modifier = modifier.padding(bottom = 32.dp),
                    heading = "Sign up",
                    navController = navController
                )
            }
            CardItem(
                buttonText = "Sign up",
                buttonClicked = {
                    //action from viewmodel
                    viewModel.signUp(
                        context = context,
                        userName = state.userName,
                        userEmail = state.email,
                        userPassword = state.password
                    )
                    if(state.userState == UserState.Success("Registered user Successfully")){
                        Toast.makeText(context, "User Signed up SuccessFully", Toast.LENGTH_SHORT).show()
                    }
                    //navigate to login to confirm signup
                    navController.navigate(
                        Screen.Login.route
                    )
                },
                emailValue = state.email,
                onEmailChangeValue = { onEvent(SignupEvents.EmailChanged(it)) },
                password = state.password,
                onPasswordChangeValue = { onEvent(SignupEvents.PasswordChanged(it)) },
                userName = state.userName,
                onUserNameChangeValue = { onEvent(SignupEvents.UsernameChanged(it)) },
            )

            Annote(
                direction = "Already have an account?",
                nextPage = "Log in",
                textClicked = {
                    navController.navigate(
                        Screen.Login.route
                    )
                }
            )
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun SignupPagePreview() {
//    AuthenticationTheme {
//        SignupPage(
//            navController = rememberNavController(),
//            viewModel = hiltViewModel<SignupViewModel>()
//        )
//    }
//}