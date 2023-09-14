package com.moovers.monitor

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.TextFieldValue
import com.moovers.monitor.presentation.AppBar
import com.moovers.monitor.presentation.MainApp
import org.junit.Rule
import org.junit.Test
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.rememberNavController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moovers.monitor.data.networking.CoroutineDispatcherProvider
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import com.moovers.monitor.domain.usecase.GetTruckUseCase
import com.moovers.monitor.presentation.truck_monitor_screen.TruckViewModel
import com.moovers.monitor.ui.theme.MooversTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.runner.RunWith
import javax.inject.Inject

@HiltAndroidTest
class ComposableTest {

    @get:Rule(order=0)
    val hiltRule = HiltAndroidRule(this)

    @JvmField
    @get:Rule(order=1)
    val composeRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setUp() {
        hiltRule.inject()
        composeRule.setContent{
            val navController = rememberNavController()
            MooversTheme {

                    MainApp()
            }

        }


    }


    @OptIn(ExperimentalMaterial3Api::class)

    @Test
    fun testAppBarContent(){

    }

}