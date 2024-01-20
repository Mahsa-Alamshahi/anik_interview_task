package com.anik.interview_task.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalMinimumInteractiveComponentEnforcement
import androidx.compose.material3.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.anik.interview_task.ui.component.theme.AnikInterviewTaskTheme
import com.anik.interview_task.ui.navigation.NavGraph
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnikInterviewTaskTheme {

                CompositionLocalProvider(
                    LocalMinimumInteractiveComponentEnforcement provides false
                ) {
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = Color.DarkGray
                    ) {
                        NavGraph()
                    }
                }
            }
        }
    }
}
