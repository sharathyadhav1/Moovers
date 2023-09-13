package com.moovers.monitor.presentation.truck_monitor_screen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.ui.graphics.vector.ImageVector


sealed class BottomMenuScreen(val route: String, val icon: ImageVector, val title: String) {

    object ListView : BottomMenuScreen("List", icon = Icons.Outlined.List, "List")
    object MapView : BottomMenuScreen("Map", icon = Icons.Outlined.Home, "Map")
}
