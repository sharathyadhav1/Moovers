package com.moovers.monitor.presentation.truck_monitor_screen

import androidx.compose.ui.graphics.vector.ImageVector
import org.junit.Assert.*
import org.junit.Test
import org.mockito.Mockito.mock

class BottomMenuScreenTest {

    @Test
    fun testListViewProperties() {

        val listView = BottomMenuScreen.ListView


        assertEquals("List", listView.route)
        assertEquals("List", listView.title)
    }

    @Test
    fun testMapViewProperties() {

        val mapView = BottomMenuScreen.MapView


        assertEquals("Map", mapView.route)
        assertEquals("Map", mapView.title)
    }
}