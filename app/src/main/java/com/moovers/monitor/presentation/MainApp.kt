package com.moovers.monitor.presentation

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CenterAlignedTopAppBar

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.moovers.monitor.R
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.presentation.truck_monitor_screen.BottomMenuScreen
import com.moovers.monitor.presentation.truck_monitor_screen.TruckViewModel
import com.moovers.monitor.presentation.truck_monitor_screen.components.BottomMenu
import java.text.SimpleDateFormat
import androidx.compose.foundation.layout.Box as Box

@Composable
fun MainApp() {
    val scrollState = rememberScrollState()
    val navController = rememberNavController()
    MainScreen(navController = navController,scrollState)
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController, scrollState: ScrollState, viewModel : TruckViewModel = hiltViewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    var truckList by remember { mutableStateOf(mutableListOf<TruckResponseItem>()) }

    val contextForToast = LocalContext.current.applicationContext

    when(uiState){
        is TruckViewModel.TruckUiState.Error -> {

        }
        is TruckViewModel.TruckUiState.Loaded -> {
            val response = (uiState as TruckViewModel.TruckUiState.Loaded).truckResponse
            truckList = response

        }
        TruckViewModel.TruckUiState.Loading -> {

        }


    }



    Scaffold(


        bottomBar ={
        BottomMenu(navController = navController)
    }) {
        Navigation(
            navController =navController , scrollState =scrollState,
            paddingValues = it,truckList)
    }
}





@Composable
fun Navigation(
    navController: NavHostController,
    scrollState: ScrollState,
    paddingValues: PaddingValues,
    truckList: MutableList<TruckResponseItem>
) {

    NavHost(navController = navController,
            startDestination = BottomMenuScreen.ListView.route,
            modifier = Modifier.padding(paddingValues)) {
        bottomNavigation(navController = navController,truckList)


    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController, truckList: MutableList<TruckResponseItem>) {

    composable(BottomMenuScreen.ListView.route) {

        Surface(){
            if (truckList != null && truckList.isNotEmpty()) {

            }
            else{
                NoTextInSurface("No items to display")
            }
        }
    }
    composable(BottomMenuScreen.MapView.route) {
        var selectedItem by remember { mutableStateOf(0) }

        Box(
            modifier = Modifier
                .fillMaxSize()

        ) {
            Surface() {
                if (truckList != null && truckList.isNotEmpty()) {
                    // Render your Composable using the non-empty list

                } else {
                    // Handle the case where the list is either null or empty
                    NoTextInSurface("No items to display")
                }

            }
        }
    }

}

@Composable
fun NoTextInSurface(text: String) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Surface( color= Color.Transparent) {
            Text(
                text = text,
                fontSize = 20.sp,

            )
        }
    }
}
