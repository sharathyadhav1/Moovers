package com.moovers.monitor.data.networking

import kotlinx.coroutines.Dispatchers
import org.junit.Assert.*
import org.junit.Test

class CoroutineDispatcherProviderTest{
    @Test
    fun testIODispatcher() {
        val dispatcherProvider = CoroutineDispatcherProvider()
        val ioDispatcher = dispatcherProvider.IO()
        assertEquals(Dispatchers.IO, ioDispatcher)
    }

    @Test
    fun testDefaultDispatcher() {
        val dispatcherProvider = CoroutineDispatcherProvider()
        val defaultDispatcher = dispatcherProvider.Default()
        assertEquals(Dispatchers.Default, defaultDispatcher)
    }

    @Test
    fun testMainDispatcher() {
        val dispatcherProvider = CoroutineDispatcherProvider()
        val mainDispatcher = dispatcherProvider.Main()
        assertEquals(Dispatchers.Main, mainDispatcher)
    }
}