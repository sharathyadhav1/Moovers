package com.moovers.monitor.domain.model

import java.text.SimpleDateFormat
import java.util.Date

data class TruckResponseItem(
    val driverName: String,
    val imageURL: String,
    val lastUpdated: String,
    val lat: Double,
    val lng: Double,
    val location: String,
    val plateNo: String
){
    val lastUpdatedDate: Date? by lazy {
        try {
            SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX").parse(lastUpdated)
        } catch (e: Exception) {
            null
        }
    }
}