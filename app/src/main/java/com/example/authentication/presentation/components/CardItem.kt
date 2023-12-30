package com.example.authentication.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.R
import com.example.authentication.ui.theme.AuthenticationTheme
import com.example.authentication.ui.theme.CustomBlue

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    buttonText: String,
    buttonClicked: () -> Unit
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(25.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = 50.dp
        )
    ) {
        Column(
            modifier = modifier
                .padding(20.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){


            var email by remember { mutableStateOf("") }
            var password by remember { mutableStateOf("") }
            var name by remember { mutableStateOf("") }

            if(buttonText == "Sign up"){
                TextFieldItem(
                    value = name,
                    onChangeValue = {
                        name = it
                    },
                    label = "Your Name",
                    icon = Icons.Default.Person,
                    modifier = modifier.padding(top = 8.dp)
                )
            }

            TextFieldItem(
                value = email,
                onChangeValue = {
                                email = it
                },
                label = "Email",
                icon = Icons.Default.Email,
                modifier = modifier.padding(top = 8.dp)
            )
            TextFieldItem(
                value = password,
                onChangeValue = {
                    password = it
                },
                label = "Password",
                icon = Icons.Default.Lock,
                modifier = modifier.padding(top = 8.dp, bottom = 8.dp)
            )
            Button(
                modifier = modifier.fillMaxWidth(),
                onClick = buttonClicked,
                colors = ButtonDefaults.buttonColors(
                    containerColor = CustomBlue,
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(50)
            ) {
                Text(
                    text = buttonText
                )
            }
            CardDivider(buttonText = buttonText)
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ){
                Divider(
                    modifier = modifier
                        .width(80.dp)
                        .padding(end = 10.dp),
                    color = Color.Black
                )
                Text(
                    text = "or Continue with"
                )
                Divider(
                    modifier = modifier
                        .width(80.dp)
                        .padding(start = 10.dp),
                    color = Color.Black
                )
            }
            CardDivider(buttonText = buttonText)
            OneClick(buttonText = "Continue with Google", icon = R.drawable.ic_google)
            OneClick(buttonText = "Continue with Facebook", icon = R.drawable.ic_facebook)
        }
    }
}

@Composable
fun TextFieldItem(
    modifier: Modifier = Modifier,
    value: String,
    onChangeValue: (String) -> Unit,
    label: String,
    icon: ImageVector,
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = onChangeValue,
        label = {
                Text(text = label)
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null
            )
        },
        shape = RoundedCornerShape(25.dp),
        singleLine = true,
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedTextColor = CustomBlue,
        )
    )
}

@Composable
fun CardDivider(
    modifier: Modifier = Modifier,
    buttonText: String
) {
    if(buttonText == "Sign up"){
        Spacer(modifier = modifier.height(40.dp))
    } else {
        Spacer(modifier = modifier.height(70.dp))
    }
}



@Preview
@Composable
fun CardItemPreview() {
    AuthenticationTheme {
        CardItem(
            buttonText = "Sign up",
            buttonClicked = {}
        )
    }
}