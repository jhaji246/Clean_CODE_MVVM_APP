package com.example.interviewtask.presentation.navigation

sealed class Screen(val route: String) {
    data object SchoolScreen: Screen("school_screen")
}