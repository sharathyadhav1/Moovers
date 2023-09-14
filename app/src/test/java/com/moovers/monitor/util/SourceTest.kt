package com.moovers.monitor.util

import org.junit.Assert.*
import org.junit.Test

class SourceTest{
    @Test
    fun testEnumValues() {
        assertEquals(Source.values().size, 3)
        assertEquals(Source.LOCAL, Source.valueOf("LOCAL"))
        assertEquals(Source.NETWORK, Source.valueOf("NETWORK"))
        assertEquals(Source.MOCK, Source.valueOf("MOCK"))
    }

    @Test
    fun testEnumToString() {
        assertEquals("LOCAL", Source.LOCAL.toString())
        assertEquals("NETWORK", Source.NETWORK.toString())
        assertEquals("MOCK", Source.MOCK.toString())
    }

    @Test
    fun testEnumEquality() {

        assertEquals(Source.LOCAL, Source.LOCAL)
        assertEquals(Source.NETWORK, Source.NETWORK)
        assertEquals(Source.MOCK, Source.MOCK)


        assert(Source.LOCAL != Source.NETWORK)
        assert(Source.NETWORK != Source.MOCK)
        assert(Source.MOCK != Source.LOCAL)
    }
}