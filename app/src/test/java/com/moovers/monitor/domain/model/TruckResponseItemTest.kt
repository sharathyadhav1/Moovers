package com.moovers.monitor.domain.model

import org.junit.Assert.*
import org.junit.Test
import java.text.SimpleDateFormat

class TruckResponseItemTest{

    @Test
    fun testLastUpdatedDateParsing() {
        // Define a sample TruckResponseItem
        val lastUpdated = "2023-09-14T15:30:00Z" // Replace with a valid date string
        val truckResponseItem = TruckResponseItem(
            "John Doe",
            "image_url",
            lastUpdated,
            40.7128,
            -74.0060,
            "New York",
            "ABC123"
        )

        // Parse the lastUpdated date using SimpleDateFormat
        val expectedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(lastUpdated)

        assertEquals(expectedDate, truckResponseItem.lastUpdatedDate)
    }

    @Test
    fun testInvalidLastUpdatedDate() {
        // Define a sample TruckResponseItem with an invalid lastUpdated date
        val invalidLastUpdated = "invalid_date_string"
        val truckResponseItem = TruckResponseItem(
            "d1",
            "image_url",
            invalidLastUpdated,
            40.7128,
            -74.0060,
            "asd",
            "asdasd"
        )

        assertNull(truckResponseItem.lastUpdatedDate)
    }

    @Test
    fun testNullableLastUpdatedDate() {
        // Define a TruckResponseItem with a null lastUpdated value
        val truckResponseItem = TruckResponseItem(
            driverName = "asd",
            imageURL = "",
            lastUpdated = "",
            lat = 34.0522,
            lng = -118.2437,
            location = "asdsad",
            plateNo = "asdasd"
        )

        // Assert that the lastUpdatedDate is null due to an empty lastUpdated string
       assertNull(truckResponseItem.lastUpdatedDate)
    }

    @Test
    fun testLastUpdatedDate() {
        // Define a TruckResponseItem with a null lastUpdated value
        val truckResponseItem = TruckResponseItem(
            driverName = "asdasdasd",
            imageURL = "asdasdasd",
            lastUpdated = "",
            lat = 34.0522,
            lng = -118.2437,
            location = "asdasdasd",
            plateNo = "asdasdasd"
        )

        assertNull(truckResponseItem.lastUpdatedDate)
    }
}