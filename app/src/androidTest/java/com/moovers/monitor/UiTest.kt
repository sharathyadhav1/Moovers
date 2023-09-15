package com.moovers.monitor

import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.moovers.monitor.data.networking.CoroutineDispatcherProvider
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import com.moovers.monitor.domain.usecase.GetTruckUseCase
import com.moovers.monitor.presentation.MainScreen
import com.moovers.monitor.presentation.truck_monitor_screen.TruckViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

@HiltAndroidTest
class UiTest {

    @get:Rule(order=0)

    val hiltRule = HiltAndroidRule(this)

    @OptIn(ExperimentalMaterial3Api::class)
    @get:Rule(order=1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Mock
    private lateinit var viewModel: TruckViewModel

    @Mock
    private lateinit var repository: TruckMonitorRepository
    @Mock
    private lateinit var getTruckUseCase: GetTruckUseCase
    @Before
    fun setUp() {
        // Define the behavior of your mock ViewModel
        MockitoAnnotations.openMocks(this)
        hiltRule.inject()
       // MockitoAnnotations.openMocks(this)

        val testDispatcherProvider = CoroutineDispatcherProvider()
    //    getTruckUseCase = GetTruckUseCase(repository)

        //MockitoAnnotations.openMocks(this)
     //   viewModel = TruckViewModel(getTruckUseCase,testDispatcherProvider)
        val mockTruckResponse = listOf(
            TruckResponseItem(driverName = "Driver Name 1", imageURL = "", lastUpdated = "", lat = 0.0, lng = 0.0, location = "", plateNo = ""),
            TruckResponseItem(driverName = "Driver Name 2", imageURL = "", lastUpdated = "", lat = 0.0, lng = 0.0, location = "", plateNo = "")
        )
        val initialState = MutableStateFlow(TruckViewModel.TruckUiState.Loaded(mockTruckResponse))

        val stateFlow: StateFlow<TruckViewModel.TruckUiState> = initialState.asStateFlow()

       // `when`(viewModel.uiState).thenReturn(stateFlow)



        // Provide the mock ViewModel to the Composable
        composeTestRule.setContent {

           // MainScreen(navController = rememberNavController(), scrollState = rememberScrollState(), viewModel =  viewModel)
        }
    }

    @Test
    fun testMainScreen() {
        // Perform UI interactions and assertions
        composeTestRule.onNodeWithText("Truck Monitor").assertIsDisplayed()

        // You can simulate user interactions if needed
        // composeTestRule.onNodeWithText("Driver Name 1").performClick()

        // Add more assertions and interactions as needed
    }
}