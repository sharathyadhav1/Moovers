package com.moovers.monitor.data.factory

import com.moovers.monitor.data.source.local.LocalTruckEntityData
import com.moovers.monitor.data.source.network.NetworkTruckEntityData
import com.moovers.monitor.util.Source
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class TruckFactoryTest{

    @Mock
    private lateinit var networkScheduleEntityData: NetworkTruckEntityData

    @Mock
    private lateinit var localScheduleEntityData: LocalTruckEntityData

    private lateinit var truckFactory: TruckFactory

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        truckFactory = TruckFactory(networkScheduleEntityData, localScheduleEntityData)
    }

    @Test
    fun testCreateNetworkSource() {
        val result = truckFactory.create(Source.NETWORK)

        assertEquals(networkScheduleEntityData, result)
    }

    @Test
    fun testCreateLocalSource() {
        val result = truckFactory.create(Source.LOCAL)

        assertEquals(localScheduleEntityData, result)
    }
}