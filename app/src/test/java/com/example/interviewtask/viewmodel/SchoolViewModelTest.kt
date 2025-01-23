package com.example.interviewtask.viewmodel

import com.example.interviewtask.core.common.Resource
import com.example.interviewtask.domain.models.School
import com.example.interviewtask.domain.usecase.GetAllSchoolUseCase
import com.example.interviewtask.presentation.state.SchoolState
import com.example.interviewtask.presentation.viewmodel.SchoolViewModel
import dagger.hilt.android.testing.HiltAndroidTest
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
@HiltAndroidTest
class SchoolViewModelTest {

    private lateinit var viewModel: SchoolViewModel
    private var useCase: GetAllSchoolUseCase = mockk()
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getAllSamples emits Loading and Success states`() = runTest {
        // Mock the usecase to emit loading and success

        val mockData = listOf(
            School(dbn = "a", city = "b", location = "c", neighborhood = "d"),
            School(dbn = "a", city = "b", location = "c", neighborhood = "d")
        )

        val flow = flow {
            emit(Resource.Loading())
            emit(Resource.Success(mockData))
        }

        coEvery { useCase() } returns flow

        // Observe The Stateflow
        val states = mutableListOf(SchoolState())
        val job = launch {
            viewModel.schoolState.toList(states)
        }

        // Advance Coroutine Dispatcher
        advanceUntilIdle()

        // Assertions
        assertTrue(states[0].isLoading)
        assertEquals(mockData, states[1].schoolItems)
        assertFalse(states[1].isLoading)

        job.cancel()
    }

    @Test
    fun `getAllSamples emits Loading and Error States`() = runTest {
        val errorMsg = "Network Error"
        val flow = flow<Resource<List<School>>> {
            emit(Resource.Loading())
            emit(Resource.Error(errorMsg))
        }

        coEvery { useCase() } returns flow

        // Observe The State Flow
        val states = mutableListOf<SchoolState>()
        val job = launch {
            viewModel.schoolState.toList(states)
        }

        // Advance Coroutine Dispatcher
        advanceUntilIdle()

        // Assertions
        assertTrue(states[0].isLoading)
        assertEquals(errorMsg, states[1].isErrorMsg)
        assertFalse(states[1].isLoading)

        job.cancel()

    }
}