package com.anik.interview_task.ui.navigation

sealed class Screen(val route: String){

    object PhoneNumber: Screen(route = "phone_number_screen")

}
