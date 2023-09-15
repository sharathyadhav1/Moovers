package com.moovers.monitor.data.mapper

import com.moovers.monitor.data.mapper.TruckResponseMapper.toTruckResponseEntity
import com.moovers.monitor.domain.model.TruckResponseItem
import org.junit.Assert.*
import org.junit.Test

class TruckResponseMapperTest{
    @Test
    fun testToTruckResponseEntity() {
        val item1 = TruckResponseItem("Driver1", "url1", "2023-09-14T10:00:00", 1.0, 2.0, "Location1", "Plate1")
        val item2 = TruckResponseItem("Driver2", "url2", "2023-09-14T11:00:00", 3.0, 4.0, "Location2", "Plate2")

        val itemList = listOf(item1, item2)

        // Act: Convert the list of items to TruckResponseEntity
        val result = itemList.toTruckResponseEntity()

        // Assert: Check if the conversion is correct
        assertEquals(2, result.size)

        assertEquals("Driver1", result[0].driverName)
        assertEquals("url1", result[0].imageURL)
        assertEquals("2023-09-14T10:00:00", result[0].lastUpdated)

        assertEquals("Location1", result[0].location)
        assertEquals("Plate1", result[0].plateNo)

        assertEquals("Driver2", result[1].driverName)
        assertEquals("url2", result[1].imageURL)
        assertEquals("2023-09-14T11:00:00", result[1].lastUpdated)

        assertEquals("Location2", result[1].location)
        assertEquals("Plate2", result[1].plateNo)
    }
}