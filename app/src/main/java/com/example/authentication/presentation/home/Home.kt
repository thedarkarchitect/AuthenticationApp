package com.example.authentication.presentation.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.authentication.presentation.login.LoginViewModel
import com.example.authentication.utils.Screen

@Composable
fun Home(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginVm: LoginViewModel
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier
                .padding(30.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(50),
            onClick = {
                //will logout action
                loginVm.logout()
                //navigate to the loginScreen
                navController.popBackStack(
                    route = Screen.Login.route,
                    inclusive = false
                )
            }
        ) {
            Text(text = "Logout")
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun HomePreview() {
//    AuthenticationTheme {
//        Home(
//            navController = rememberNavController()
//        )
//    }
//}