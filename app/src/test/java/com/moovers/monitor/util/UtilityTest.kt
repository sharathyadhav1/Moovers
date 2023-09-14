package com.moovers.monitor.util

import com.moovers.monitor.util.Utility.getTimeAgo
import com.moovers.monitor.util.Utility.stringToDate
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.TimeZone

class UtilityTest {

    @Before
    fun setUp() {

    }

    @Test
    fun testGetTimeAgo() {

        val dateInThePast = Calendar.getInstance().apply {
            set(2022, Calendar.AUGUST, 1, 12, 30)
        }.time


        val timeAgo = dateInThePast.getTimeAgo()


        assertEquals("1 year ago", timeAgo)
    }

    @Test
    fun testStringToDate() {

        val dateStr = "2023-09-14T15:30:00+00:00"


        val date = stringToDate(dateStr)


        val expectedDate = Calendar.getInstance().apply {
            set(2023, Calendar.SEPTEMBER, 14, 15, 30, 0)
            timeZone = TimeZone.getTimeZone("UTC")
        }.time


        assertNotSame(expectedDate, date)
    }
}