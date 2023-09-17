package com.moovers.monitor.presentation.truck_monitor_screen.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.util.TestTags.LIST_SECTION


@Composable
fun TruckListView(navController: NavController, truckList: MutableList<TruckResponseItem>) {



    Column(modifier = Modifier.fillMaxSize(),horizontalAlignment = Alignment.CenterHorizontally) {
       //SearchView(query,newsManager)


        ContentInColum(truckList)


           }
}


@Composable
fun ContentInColum(response:  MutableList<TruckResponseItem>) {

    Column {
        //  TopTitle(forColumn = false, alpha = rowAlpha)
        LazyColumn(
            modifier = Modifier.background(Color.Transparent).testTag(LIST_SECTION)
        ) {
            itemsIndexed(response) { index, item ->
                ListItem(item){
                    println(index)

                }
            }
        }
    }
}








