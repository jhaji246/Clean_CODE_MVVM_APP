package com.example.interviewtask.presentation.state

import com.example.interviewtask.domain.models.School

data class SchoolState(
    val schoolItems: List<School>? = emptyList(),
    val isErrorMsg: String? = "",
    val isLoading: Boolean = false
)

