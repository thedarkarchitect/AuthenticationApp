package com.example.authentication.presentation.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.R
import com.example.authentication.presentation.components.Annote
import com.example.authentication.presentation.components.CardItem
import com.example.authentication.presentation.components.TopHeading
import com.example.authentication.ui.theme.AuthenticationTheme


@Composable
fun LoginPage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(id = R.drawable.ic_background),
            contentDescription = null,
            modifier = modifier.matchParentSize()
        )
        Column(
            modifier = modifier
                .padding(25.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Box(
                modifier = modifier.fillMaxWidth()
            ){
                TopHeading(
                    modifier = modifier.padding(bottom = 32.dp),
                    heading = "Log in"
                )
            }
            CardItem(buttonText = "Log in")

            Annote(
                direction = "Don't have an Account?",
                nextPage = "SignUp"
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun LoginPagePreview() {
    AuthenticationTheme {
        LoginPage()
    }
}