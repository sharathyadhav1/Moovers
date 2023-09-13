package com.moovers.monitor.presentation.truck_monitor_screen.components



import androidx.compose.material3.Icon
import androidx.compose.material3.LocalAbsoluteTonalElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.moovers.monitor.presentation.truck_monitor_screen.BottomMenuScreen
import com.moovers.monitor.ui.theme.lightBlue


@Composable
fun BottomMenu(navController:NavController) {
    val menuItems = listOf(
        BottomMenuScreen.ListView,
        BottomMenuScreen.MapView,
    )

    NavigationBar(contentColor = MaterialTheme.colorScheme.primary, containerColor = MaterialTheme.colorScheme.primary)
    {

        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        menuItems.forEach {
            NavigationBarItem(
                label = { Text(text = it.title , style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    unselectedTextColor = MaterialTheme.colorScheme.lightBlue,
                    unselectedIconColor = MaterialTheme.colorScheme.lightBlue,
                    selectedTextColor = Color.White,
                    selectedIconColor= Color.White,
                    indicatorColor = MaterialTheme.colorScheme.surfaceColorAtElevation(LocalAbsoluteTonalElevation.current)

                ),

                selected = currentRoute == it.route,
                onClick = {
                    navController.navigate(it.route) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = it.icon,
                        contentDescription = it.title
                    )
                },
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomMenuPreview() {

}