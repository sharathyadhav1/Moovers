package com.moovers.monitor.data.mapper

import com.moovers.monitor.data.local.TruckResponseEntity
import com.moovers.monitor.data.mapper.TruckEntityMapper.toTruckResponseItem
import org.junit.Assert.*
import org.junit.Test

class TuckEntityMapperTest{

    @Test
    fun testToTruckResponseItem() {
        val entityList = listOf(
            TruckResponseEntity("Driver1", "url1", "2023-09-14T10:00:00", 1.0, 2.0, "Location1", "Plate1"),
            TruckResponseEntity("Driver2", "url2", "2023-09-14T11:00:00", 3.0, 4.0, "Location2", "Plate2")
        )

        val result = entityList.toTruckResponseItem()

        // Assert: Check if the conversion is correct
        assertEquals(2, result.size)

        assertEquals("Driver1", result[0].driverName)
        assertEquals("url1", result[0].imageURL)
        assertEquals("2023-09-14T10:00:00", result[0].lastUpdated)






    }
}