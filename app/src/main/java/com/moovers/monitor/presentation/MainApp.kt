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
import androidx.compose.material3.CircularProgressIndicator

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
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
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
import com.moovers.monitor.presentation.truck_monitor_screen.components.SearchView
import com.moovers.monitor.presentation.truck_monitor_screen.screen.ContentInRow
import com.moovers.monitor.presentation.truck_monitor_screen.screen.TruckListView
import com.moovers.monitor.presentation.truck_monitor_screen.screen.TruckMapView
import com.moovers.monitor.util.TestTags
import com.moovers.monitor.util.TestTags.BTN_ACTION

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
    val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX")
    var truckList by remember { mutableStateOf(mutableListOf<TruckResponseItem>()) }
    var isAscending by remember { mutableStateOf(true) }
    var progressState by remember { mutableStateOf(true) }
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val contextForToast = LocalContext.current.applicationContext

    when(uiState){
        is TruckViewModel.TruckUiState.Error -> {
            progressState= false
        }
        is TruckViewModel.TruckUiState.Loaded -> {
            val response = (uiState as TruckViewModel.TruckUiState.Loaded).truckResponse
            truckList = response
            progressState= false
        }
        TruckViewModel.TruckUiState.Loading -> {
            progressState= true
        }


    }

    // Function to filter the list based on the search query
    fun filterList(searchText: String): MutableList<TruckResponseItem> {
        return if (searchText.isEmpty()) {
            // If search query is empty, return the original list
            truckList
        } else {
            // Use filter to find items that contain the search query
            truckList.filter { item ->
                item.driverName.contains(searchText, ignoreCase = true)
            }.toMutableList()
        }
    }
    println(textState.value.text)


    if (isAscending) {
        truckList.sortWith(compareBy<TruckResponseItem> { dateFormat.parse(it.lastUpdated) })
    } else {
        truckList.sortWith(compareByDescending<TruckResponseItem> { dateFormat.parse(it.lastUpdated) })
    }

    Scaffold(
        topBar = { AppBar( textState) {
            isAscending = !isAscending
        } },

        bottomBar ={
            BottomMenu(navController = navController)
        }) {
        showProgress(progressState)
        Navigation(
            navController =navController , scrollState =scrollState,
            paddingValues = it,filterList(textState.value.text))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(textState: MutableState<TextFieldValue>, buttonClick: () -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentHeight()
        .background(color = MaterialTheme.colorScheme.primary)) {

        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(R.string.truck_monitor), color = Color.White,fontWeight = FontWeight.Bold) },

            actions = {
                val drawableResId = R.drawable.baseline_swap_vert_white_36
                val painter: Painter = painterResource(id = drawableResId)

                IconButton(onClick = buttonClick, modifier = Modifier.testTag(BTN_ACTION)) {
                    Icon( painter = painter, contentDescription = stringResource(R.string.sort_items),tint = Color.White)
                }
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.primary
            )
        )

        SearchView(textState)
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
        modifier = Modifier
            .padding(paddingValues)
            .testTag(TestTags.BOTTOM_TAB_SECTION)) {
        bottomNavigation(navController = navController,truckList)


    }
}

fun NavGraphBuilder.bottomNavigation(navController: NavController, truckList: MutableList<TruckResponseItem>) {

    composable(BottomMenuScreen.ListView.route) {

        Surface(){
            if (truckList != null && truckList.isNotEmpty()) {
                TruckListView(navController = navController, truckList)
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
                    TruckMapView(truckList[selectedItem])
                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)

                            .fillMaxWidth()
                            .background(Color.Transparent)
                    ) {
                        ContentInRow(truckList){
                            selectedItem = it
                        }
                    }
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
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTags.NO_TEXT_SECTION),
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
@Composable
fun showProgress(isShown: Boolean) {

    val commentsAlpha = if (isShown) 1f else 0f


    Box(
        modifier = Modifier
            .fillMaxSize()
            .testTag(TestTags.PROGRESS_BAR_SECTION), contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(40.dp)
                .testTag(TestTags.CIRCLE_PROGRESS_BAR_SECTION)
                .alpha(commentsAlpha),
            color = MaterialTheme.colorScheme.primary,
            strokeWidth = 2.dp
        )
    }

}
