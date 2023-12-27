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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.authentication.ui.theme.AuthenticationTheme

@Composable
fun Home(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            modifier = modifier.padding(30.dp).fillMaxWidth(),
            shape = RoundedCornerShape(50),
            onClick = { /*TODO*/ }
        ) {
            Text(text = "Logout")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    AuthenticationTheme {
        Home()
    }
}