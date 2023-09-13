package com.moovers.monitor.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "truck_data")
data class TruckResponseEntity(
    val driverName: String="",
    val imageURL: String="",
    val lastUpdated: String="",
    val lat: Double,
    val lng: Double,
    val location: String="",
    @PrimaryKey val plateNo: String
)