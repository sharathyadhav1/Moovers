package com.moovers.monitor.data.source.network

import com.moovers.monitor.data.remote.TruckMonitorApi
import com.moovers.monitor.domain.model.TruckResponseItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class NetworkTruckEntityDataTest{

    private lateinit var networkTruckEntityData: NetworkTruckEntityData

    // Mocked dependency
    @Mock
    private lateinit var truckMonitorApi: TruckMonitorApi

    @Before
    fun setUp() {
        // Initialize the mocked dependencies
        MockitoAnnotations.initMocks(this)

        // Create an instance of the class under test with the mocked TruckMonitorApi
        networkTruckEntityData = NetworkTruckEntityData(truckMonitorApi)
    }

    @Test
    fun `test getTruckDetails success`() = runBlocking {
        // Arrange
        val expectedTruckDetails = mutableListOf<TruckResponseItem>() // Define your expected data here
        `when`(truckMonitorApi.getTruckDetails()).thenReturn(expectedTruckDetails)

        // Act
        val result = networkTruckEntityData.getTruckDetails()

        // Assert
        assert(result == expectedTruckDetails)
    }

    @Test(expected = NotImplementedError::class)
    fun testAddTruckDetailsNotImplemented() = runBlocking {
        val emptyIntList = mutableListOf<TruckResponseItem>()

        networkTruckEntityData.addTruckDetails(emptyIntList)

    }
}