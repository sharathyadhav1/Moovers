package com.moovers.monitor.util

import com.moovers.monitor.util.Utility.getTimeAgo
import com.moovers.monitor.util.Utility.stringToDate
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.Calendar
import java.util.Date
import java.util.TimeZone

class UtilityTest {

    private lateinit var currentDate: Date


    @Before
    fun setUp() {
        val calendar = Calendar.getInstance()
        calendar.set(2023, Calendar.SEPTEMBER, 1, 12, 0, 0) // September 1, 2023, 12:00 PM
        currentDate = calendar.time
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
    fun testYearsAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.YEAR, -2) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("2 years ago", timeAgo)
    }

    @Test
    fun testGetMonthAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -1) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
       assertEquals("1 month ago", timeAgo)
    }

    @Test
    fun testGetMonthsAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MONTH, -2) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("2 months ago", timeAgo)
    }

    @Test
    fun testDayAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -1) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("1 day ago", timeAgo)
    }


    @Test
    fun testDaysAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DAY_OF_MONTH, -2) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("2 days ago", timeAgo)
    }

    @Test
    fun testHourAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, -1) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("1 hour ago", timeAgo)
    }

    @Test
    fun testHoursAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.HOUR_OF_DAY, -2) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("2 hours ago", timeAgo)
    }

    @Test
    fun testGetTimeMinAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -15) // Subtract 15 minutes from the current time
        val fifteenMinutesAgo = calendar.time
        val timeAgo = fifteenMinutesAgo.getTimeAgo()
        assertEquals("15 minutes ago", timeAgo)
    }



    @Test
    fun testGetTimeAgoDaysAgo() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -30) // Subtract 15 minutes from the current time
        val thirtyMinutesAgo = calendar.time
        val timeAgo = thirtyMinutesAgo.getTimeAgo()
        assertEquals("30 minutes ago", timeAgo)
    }

    @Test
    fun testGetTimeAgoCurrentTime() {
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.MINUTE, -1) // Subtract 15 minutes from the current time
        val oneMinutesAgo = calendar.time
        val timeAgo = oneMinutesAgo.getTimeAgo()
        assertEquals("1 minute ago", timeAgo)
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



    private fun createDate(year: Int, month: Int, day: Int, hour: Int, minute: Int): Date {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, hour, minute)
        return calendar.time
    }
}