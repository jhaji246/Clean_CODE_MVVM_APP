package com.example.interviewtask.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.interviewtask.presentation.state.SchoolState

@Composable
fun SchoolItemScreen(modifier: Modifier, schoolState: SchoolState) {

    if (schoolState.isLoading) {
        Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = modifier.align(Alignment.Center))
        }
    } else if (!schoolState.isErrorMsg.isNullOrEmpty()) {
        Box(modifier = modifier.fillMaxSize()) {
            Text(text = schoolState.isErrorMsg.toString(), modifier = modifier.align(Alignment.Center))
        }
    }
    if (schoolState.schoolItems?.isNotEmpty()!!) {
        LazyColumn {
            items(schoolState.schoolItems) {
                SchoolItem(modifier = modifier, sample = it)
            }
        }
    }
}