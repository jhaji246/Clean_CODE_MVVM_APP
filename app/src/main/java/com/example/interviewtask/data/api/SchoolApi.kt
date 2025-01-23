package com.example.interviewtask.data.api

import com.example.interviewtask.data.dto.SchoolDto
import retrofit2.http.GET

interface SchoolApi {
    @GET("s3k6-pzi2")
    suspend fun getAllSchools(): List<SchoolDto>
}