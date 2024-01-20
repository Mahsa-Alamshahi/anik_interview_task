package com.anik.interview_task.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun EmptyVerificationCodeListView(errorMessage: String, onRetry: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .align(Alignment.Center)
                .background(Color.Black.copy(alpha = .5f))
        ) {


            Text(
                errorMessage, color = Color.White
            )


            IconButton(onClick = onRetry) {
                Icon(
                    Icons.Default.Refresh, contentDescription = "Retry Icon"
                )
            }
        }
    }
}