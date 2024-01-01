package com.example.authentication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.authentication.utils.Screen

@Composable
fun TopHeading(
    modifier: Modifier = Modifier,
    heading: String,
    navController: NavController
) {
    Row(
        verticalAlignment = Alignment.Bottom
    ){
        if(heading == "Sign up"){
            Icon(
                modifier = modifier
                    .align(Alignment.CenterVertically)
                    .padding(end = 8.dp)
                    .size(40.dp)
                    .clickable(
                        onClick = {
                            navController.popBackStack(
                                route = Screen.Login.route,
                                inclusive = false
                            )
                        }
                    ),
                imageVector = Icons.Default.ArrowBack,
                tint = Color.White ,
                contentDescription = null
            )
        }
        Text(
            text = heading,
            color = Color.White,
            fontSize = 40.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = modifier
//                .padding( bottom = 32.dp)
                .align(Alignment.Bottom)
        )
    }
}