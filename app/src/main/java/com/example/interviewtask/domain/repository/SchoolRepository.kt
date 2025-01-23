package com.example.interviewtask.domain.repository

import com.example.interviewtask.core.common.Resource
import com.example.interviewtask.domain.models.School
import kotlinx.coroutines.flow.Flow

interface SchoolRepository {
    fun getAllSchools(): Flow<Resource<List<School>>>
}