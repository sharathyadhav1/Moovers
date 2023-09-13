package com.moovers.monitor.presentation.truck_monitor_screen.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.LocalContentColor

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMapOptions
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.util.Utility
import com.moovers.monitor.util.Utility.getTimeAgo

@Composable
fun TruckMapView(truckResponseItem: TruckResponseItem) {
    println("asd"+truckResponseItem.driverName)
    var markerPosition by remember { mutableStateOf(LatLng(0.0, 0.0)) }

    //LaunchedEffect(truckResponseItem) {
        markerPosition = LatLng(truckResponseItem.lat, truckResponseItem.lng)
    //}


    val context = LocalContext.current
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition(markerPosition, 12f,0f,0f)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoomPreference = 16f,
                minZoomPreference = 5f,
            )
        )
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(mapToolbarEnabled = false)
        )
    }
    GoogleMap(

        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings
    ){
        Marker(
            state = MarkerState(position = markerPosition),
            title = truckResponseItem.plateNo,
            snippet = truckResponseItem.driverName
        )
    }
    //cameraPositionState.move(CameraUpdateFactory.zoomIn())


}



@Composable
fun ContentInRow(truckList: MutableList<TruckResponseItem>, onItemClick: (Int) -> Unit) {



    Column {
        //  TopTitle(forColumn = false, alpha = rowAlpha)
        LazyRow(
            modifier = Modifier.background(Color.Transparent)
        ) {
            itemsIndexed(truckList) { index, item ->
                ListItem(item){
                     println(index)
                    onItemClick.invoke(index)
                }
            }
        }
    }
}


@Composable
fun ListItem(truckResponseItem :TruckResponseItem,onItemClick: () -> Unit){
    Column {
        Card(
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.background
            ),
            modifier = Modifier
                .width(400.dp)
                .padding(8.dp)
                .clickable { onItemClick()}
        ) {
            Row {
                Image(
                    painter = rememberImagePainter("https://www.themealdb.com//images//category//goat.png"),
                    contentDescription = null,

                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterVertically)
                        .size(84.dp)
                        .clip(RoundedCornerShape(corner = CornerSize(16.dp)))
                )


                Column(
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(16.dp)
                ) {

                    DetailRow("Plate no: ",truckResponseItem.plateNo)
                    Spacer(Modifier.height(4.dp))
                    DetailRow("Driver Name: ",truckResponseItem.driverName)
                    Spacer(Modifier.height(4.dp))
                    DetailRow("Location: ",truckResponseItem.location)
                    Spacer(Modifier.height(4.dp))
                    DetailRow("Last update: ",
                        Utility.stringToDate(truckResponseItem.lastUpdated?:"2021-11-05T13:32:14Z").getTimeAgo())

                }
            }
        }


    }


}

@Composable
fun DetailRow( title:String,value:String){

    Row{
        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.primary


        )
        CompositionLocalProvider(LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(alpha = 0.60f)) {
            Text(
                text = value,
                style = MaterialTheme.typography.labelLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }

}

