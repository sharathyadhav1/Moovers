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
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moovers.monitor.presentation.truck_monitor_screen.TruckViewModel
import com.moovers.monitor.ui.theme.MooversTheme
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ComposableTest {


    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @JvmField
    @Rule
    val composeTestRule = createComposeRule()


    @Inject
    lateinit var truckViewModel: TruckViewModel

    @Before
    fun init() {
        hiltRule.inject()
    }


    @OptIn(ExperimentalMaterial3Api::class)

    @Test
    @Composable
    fun testAppBarContent(){
        val textState = remember { mutableStateOf(TextFieldValue())}
        // Define a flag to check if the button is clicked


            composeTestRule.setContent {
                AppBar(textState = textState, buttonClick = {})
        }

        // Verify the presence of the AppBar title
        composeTestRule.onNodeWithText("Truck Monitor", useUnmergedTree = true).assertIsDisplayed()

        // Find and click the IconButton
        composeTestRule.onNodeWithContentDescription("Sort Items").performClick()

        // Verify that the buttonClick callback was invoked

        // Verify the presence of the SearchView
        composeTestRule.onNodeWithTag("SearchViewTag").assertIsDisplayed()
    }

}