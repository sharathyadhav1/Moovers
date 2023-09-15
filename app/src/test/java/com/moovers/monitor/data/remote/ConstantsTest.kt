package com.moovers.monitor.data.remote

import org.junit.Assert.*
import org.junit.Test

class ConstantsTest{
    @Test
    fun testBaseUrl() {

        val expectedBaseUrl = "https://interviewtestapi.azurewebsites.net/"


        val actualBaseUrl = Constants.BASE_URL

        assertEquals(expectedBaseUrl, actualBaseUrl)
    }
}