package com.anik.interview_task.ui.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.anik.interview_task.ui.register.PhoneNumberScreenRoute
import com.anik.interview_task.ui.register.VerificationCodeScreenRoute

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.PhoneNumber.route) {
        phoneNumberRoute(navController)
        verificationCodeRoute(navController)
    }
}


fun NavGraphBuilder.phoneNumberRoute(navController: NavController) {
    composable(
        route = Screen.PhoneNumber.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
    ) {

        PhoneNumberScreenRoute() {
            navController.navigate(Screen.VerificationCode.route)
        }
    }
}


fun NavGraphBuilder.verificationCodeRoute(navController: NavController) {
    composable(
        route = Screen.VerificationCode.route,
        enterTransition = {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Companion.Right,
                animationSpec = tween(700)
            )
        },
    ) {

        VerificationCodeScreenRoute(){
            navController.popBackStack()
        }

    }
}


