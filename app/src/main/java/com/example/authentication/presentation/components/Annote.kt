package com.example.authentication.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp

@Composable
fun Annote(
    modifier: Modifier = Modifier,
    direction: String,
    nextPage: String,
    textClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.Start
    ){
        Text(
            text = buildAnnotatedString {
                append(direction)
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append(" $nextPage")
                }
            },
            modifier = modifier
                .padding(top = 50.dp)
                .clickable (
                    onClick = textClicked
                )
        )
    }
}