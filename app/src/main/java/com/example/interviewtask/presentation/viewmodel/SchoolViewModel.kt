package com.example.interviewtask.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.interviewtask.core.common.Resource
import com.example.interviewtask.domain.usecase.GetAllSchoolUseCase
import com.example.interviewtask.presentation.state.SchoolState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class SchoolViewModel @Inject constructor(private val useCase: GetAllSchoolUseCase): ViewModel() {

    private val _schoolState = MutableStateFlow(SchoolState())
    val schoolState: StateFlow<SchoolState>
        get() = _schoolState

    init {
        getAllSchoolItemsDatas()
    }

    private fun getAllSchoolItemsDatas() {
        useCase().onEach {
            when(it) {
                is Resource.Success -> {
                    _schoolState.value = SchoolState().copy(schoolItems = it.data)
                }
                is Resource.Error -> {
                    _schoolState.value = SchoolState().copy(isErrorMsg = it.msg)
                }
                is Resource.Loading -> {
                    _schoolState.value = SchoolState().copy(isLoading = false)
                }
            }
        }.launchIn(viewModelScope)
    }
}