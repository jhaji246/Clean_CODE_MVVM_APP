package com.example.interviewtask.data.repository

import com.example.interviewtask.core.common.Resource
import com.example.interviewtask.data.api.SchoolApi
import com.example.interviewtask.data.mapper.toDomainSchool
import com.example.interviewtask.domain.models.School
import com.example.interviewtask.domain.repository.SchoolRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SchoolRepositoryImpl @Inject constructor(private val schoolApi: SchoolApi): SchoolRepository {

    override fun getAllSchools(): Flow<Resource<List<School>>> = flow{
        emit(Resource.Loading())
        val result = schoolApi.getAllSchools().map {
            it.toDomainSchool()
        }
        emit(Resource.Success(result))
    }.flowOn(Dispatchers.IO)
        .catch {
            emit(Resource.Error(it.message.toString()))
        }

}