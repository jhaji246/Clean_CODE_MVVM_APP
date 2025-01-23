package com.example.interviewtask.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.interviewtask.presentation.components.SchoolItemScreen
import com.example.interviewtask.presentation.viewmodel.SchoolViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.SchoolScreen.route) {
        composable(Screen.SchoolScreen.route) {
            val schoolViewModel = hiltViewModel<SchoolViewModel>()
            val schoolState = schoolViewModel.schoolState.collectAsStateWithLifecycle().value
            SchoolItemScreen(modifier = Modifier, schoolState = schoolState)
        }
    }
}