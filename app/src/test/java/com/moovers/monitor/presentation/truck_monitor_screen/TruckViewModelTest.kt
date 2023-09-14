package com.moovers.monitor.presentation.truck_monitor_screen

import androidx.annotation.StringRes
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.moovers.monitor.ExceptionParser
import com.moovers.monitor.data.networking.CoroutineDispatcherProvider
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import com.moovers.monitor.domain.usecase.GetTruckUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.StandardTestDispatcher
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoMoreInteractions
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import org.mockito.hamcrest.MockitoHamcrest.argThat
import java.text.SimpleDateFormat
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class TruckViewModelTest {



    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
    private val dispatcher = StandardTestDispatcher()

    // Mock dependencies

    private lateinit var getTruckUseCase: GetTruckUseCase


    @Mock
    lateinit var truckUiStateObserver: Observer<TruckViewModel.TruckUiState>

    private lateinit var viewModel: TruckViewModel

    @Mock
    private lateinit var repository: TruckMonitorRepository


   /* private val listOfTrucks = (0..2).map{
        val truckResponseItem = TruckResponseItem(
            "John Doe",
            "image_url",
            "2023-09-14T15:30:00Z",
            40.7128,
            -74.0060,
            "New York",
            "ABC123"
        )
    }*/
    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        val testDispatcherProvider = CoroutineDispatcherProvider()
        getTruckUseCase = GetTruckUseCase(repository)
        // Initialize your ViewModel with mock dependencies
        viewModel = TruckViewModel(getTruckUseCase, testDispatcherProvider)


    }

    @After
    fun teardown() {

    }




    @Test
    fun loadedStateTest() {
        val truckResponse = mutableListOf( TruckResponseItem("Wyatt Liam", "https://i.picsum.photos/id/583/200/300.jpg", "2023-09-14T15:30:00Z", 25.357119, 55.391068, "Rolla, Sharjah, the UAE", "X 19599")
        )
        val loadedState = TruckViewModel.TruckUiState.Loaded(truckResponse)

        assertEquals(true, loadedState is TruckViewModel.TruckUiState.Loaded)

        assertEquals(truckResponse, loadedState.truckResponse)
    }


}