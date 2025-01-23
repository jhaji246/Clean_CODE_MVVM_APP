package com.example.interviewtask.domain.usecase

import com.example.interviewtask.domain.repository.SchoolRepository
import javax.inject.Inject


class GetAllSchoolUseCase @Inject constructor(private val schoolRepository: SchoolRepository) {
    operator fun invoke() = schoolRepository.getAllSchools()
}