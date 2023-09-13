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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.moovers.monitor.domain.model.TruckResponseItem


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
            modifier = Modifier.background(Color.Transparent)
        ) {
            itemsIndexed(response) { index, item ->
                TruckItem(item){
                    println(index)

                }
            }
        }
    }
}

@Composable
fun TruckItem(appResponseItem: TruckResponseItem, onNewsClick: () -> Unit = {},) {
    Box(modifier = Modifier
        .height(200.dp)
        .padding(8.dp)
        .clickable {
            onNewsClick()
        }) {
        Card(
            elevation = CardDefaults.outlinedCardElevation(),
            modifier = Modifier
                .size(width = 280.dp, height = 200.dp)
                .padding(8.dp)
                .clickable { }
        ){
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable { }
                    .padding(top = 12.dp, bottom = 12.dp)
            ){

            }

        }
    }
}




@Composable
private fun ExploreImage(item: TruckResponseItem?) {
    AsyncImage(
        model = item?.imageURL,
        contentDescription = null,
    )
}


@Preview(showBackground = true)
@Composable
fun TopNewsPreview() {
    TruckItem(  TruckResponseItem(
        plateNo = "X 19599",
        driverName = "Wyatt Liam",
        lat = 25.357119,
        lng = 55.391068,
        location = "Rolla, Sharjah, the UAE",
        imageURL = "https://i.picsum.photos/id/583/200/300.jpg",
        lastUpdated = "2023-09-10T01:02:04+00:00"

    )
    )
}
