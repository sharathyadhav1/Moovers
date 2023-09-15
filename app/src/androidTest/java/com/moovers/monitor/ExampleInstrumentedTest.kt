package com.moovers.monitor

import android.content.Context
import android.content.Intent
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.moovers.monitor.util.TestTags
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MooversUiTest {
    val targetContext: Context = InstrumentationRegistry.getInstrumentation().targetContext


    @get:Rule(order = 0)
    var hiltRule = HiltAndroidRule(this)

    @get:Rule(order=1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()


    @Before
    fun setup() {
        hiltRule.inject()
    }

    @Test
    fun check_title_of_ActionBar(){
        val title_text = composeTestRule.activity.getString(R.string.truck_monitor,0)
        composeTestRule.onNodeWithText(title_text).assertExists()
    }
    @Test
    fun check_search(){
        val search_text = composeTestRule.activity.getString(R.string.search,0)
        composeTestRule.onNodeWithText(search_text).assertExists()
    }

    @Test
    fun check_tab_navigation_list(){
        val list_text = "List"
        composeTestRule.onNodeWithText(list_text).assertExists()
    }

    @Test
    fun check_tab_navigation_map(){
        val map_text = composeTestRule.activity.getString(R.string.search,0)
        composeTestRule.onNodeWithText(map_text).assertExists()
    }

    @Test
    fun search_tab_content_Description(){
        val search_text_description = composeTestRule.activity.getString(R.string.search_content,0)
        composeTestRule.onNodeWithContentDescription(search_text_description).assertExists()
    }

    @Test
    fun search_close_content_Description(){
        val search_text_description = composeTestRule.activity.getString(R.string.search_close,0)
        composeTestRule.onNodeWithContentDescription(search_text_description).assertExists()
    }

    @Test
    fun search_view_visibility(){
        composeTestRule.onNodeWithTag(TestTags.SEARCH_SECTION).assertExists()
    }

}