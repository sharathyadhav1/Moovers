package com.moovers.monitor.data.factory

import com.moovers.monitor.data.source.local.LocalTruckEntityData
import com.moovers.monitor.data.source.network.NetworkTruckEntityData
import com.moovers.monitor.util.Source
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class TruckFactoryTest{
    @Mock
    lateinit var networkScheduleEntityData: NetworkTruckEntityData

    @Mock
    lateinit var localScheduleEntityData: LocalTruckEntityData

    private lateinit var truckFactory: TruckFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        truckFactory = TruckFactory(networkScheduleEntityData, localScheduleEntityData)
    }



  }