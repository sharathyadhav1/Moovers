package com.moovers.monitor

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.MutableState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.TextFieldValue
import org.junit.Rule
import org.junit.Test
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.presentation.MainScreen
import com.moovers.monitor.presentation.truck_monitor_screen.TruckViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before

@HiltAndroidTest
class ComposableTest {

    @get:Rule(order=0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order=1)
    val composeRule = createAndroidComposeRule<ComponentActivity>()

    private lateinit var state: MutableState<TextFieldValue>



    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent {
           // MainScreen(navController = mockk(), scrollState = rememberScrollState(), viewModel = viewModel)
        }


    }





}