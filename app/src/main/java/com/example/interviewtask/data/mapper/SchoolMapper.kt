package com.example.interviewtask.data.mapper

import com.example.interviewtask.data.dto.SchoolDto
import com.example.interviewtask.domain.models.School

fun SchoolDto.toDomainSchool() : School {
    return School(dbn, city, location, neighborhood)
}