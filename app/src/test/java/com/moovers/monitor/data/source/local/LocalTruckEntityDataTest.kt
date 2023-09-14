package com.moovers.monitor.data.source.local

import com.moovers.monitor.data.local.TruckDetailsDao
import com.moovers.monitor.data.local.TruckResponseEntity
import com.moovers.monitor.data.mapper.TruckEntityMapper.toTruckResponseItem
import com.moovers.monitor.data.mapper.TruckResponseMapper.toTruckResponseEntity
import com.moovers.monitor.domain.model.TruckResponseItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

class LocalTruckEntityDataTest{

    private lateinit var localTruckEntityData: LocalTruckEntityData
    private lateinit var truckDetailsDao: TruckDetailsDao

    @Before
    fun setUp() {
        // Create a mock for the TruckDetailsDao
        truckDetailsDao = mock(TruckDetailsDao::class.java)
        localTruckEntityData = LocalTruckEntityData(truckDetailsDao)
    }

    @Test
    fun testGetTruckDetails() = runBlocking {
        val truckEntities = listOf(
            TruckResponseEntity("Wyatt Liam", "https://i.picsum.photos/id/583/200/300.jpg", "2023-09-14T13:34:33+00:00", 25.357119, 55.391068, "Rolla, Sharjah, the UAE", "X 19599",)
        ).toMutableList()

        `when`(truckDetailsDao.getTruck()).thenReturn(truckEntities)

        val result = localTruckEntityData.getTruckDetails()

        assertEquals(truckEntities.toTruckResponseItem(), result)
    }

    @Test
    fun testAddTruckDetails() = runBlocking {
        val truckResponse = mutableListOf(
            TruckResponseItem("Wyatt Liam", "https://i.picsum.photos/id/583/200/300.jpg", "2023-09-14T15:30:00Z", 25.357119, 55.391068, "Rolla, Sharjah, the UAE", "X 19599")
        )

        // Convert the TruckResponseItem list to TruckResponseEntity
        val truckEntities = truckResponse.toTruckResponseEntity()

        localTruckEntityData.addTruckDetails(truckResponse)

        verify(truckDetailsDao).insertTruck(truckEntities)
    }
}