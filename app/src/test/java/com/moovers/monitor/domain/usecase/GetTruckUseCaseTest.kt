package com.moovers.monitor.domain.usecase

import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import kotlinx.coroutines.runBlocking

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetTruckUseCaseTest{


    private lateinit var getTruckUseCase: GetTruckUseCase

    // Mocked dependencies
    @Mock
    private lateinit var repository: TruckMonitorRepository

    @Before
    fun setUp() {
        // Initialize the mocked dependencies
        MockitoAnnotations.initMocks(this)

        // Create an instance of the class under test with the mocked repository
        getTruckUseCase = GetTruckUseCase(repository)
    }

    @Test
    fun `test execute`() = runBlocking {
        // Arrange
        val expectedTruckList = mutableListOf<TruckResponseItem>()

        // Mock the behavior of the repository
        `when`(repository.fetchTruckDetails()).thenReturn(expectedTruckList)

        // Act
        val result = getTruckUseCase.execute()

        // Assert
        assert(result == expectedTruckList)
    }

}