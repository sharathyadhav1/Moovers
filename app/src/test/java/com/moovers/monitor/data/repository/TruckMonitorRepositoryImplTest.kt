package com.moovers.monitor.data.repository

import com.moovers.monitor.data.factory.TruckFactory
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.util.Source
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TruckMonitorRepositoryImplTest{

    @Mock
    lateinit var truckFactory: TruckFactory

    private lateinit var repository: TruckMonitorRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        repository = TruckMonitorRepositoryImpl(truckFactory)
    }


/*    @Test
    fun testFetchTruckDetailsWithLocalSource() = runBlocking {
        // Arrange
        val localSource = Source.LOCAL
        val truckResponse = mutableListOf( TruckResponseItem("Wyatt Liam", "https://i.picsum.photos/id/583/200/300.jpg", "2023-09-14T15:30:00Z", 25.357119, 55.391068, "Rolla, Sharjah, the UAE", "X 19599"))

        `when`(truckFactory.create(localSource).getTruckDetails()).thenReturn(truckResponse)

        // Act
        val result = runBlocking { repository.fetchTruckDetails() }

        // Assert
        assert(result === truckResponse)
        // Add more assertions based on your specific use case
    }*/



}