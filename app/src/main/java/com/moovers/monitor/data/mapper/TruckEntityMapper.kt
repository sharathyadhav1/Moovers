package com.moovers.monitor.data.mapper

import com.moovers.monitor.data.local.TruckResponseEntity
import com.moovers.monitor.domain.model.TruckResponseItem


object TruckEntityMapper {

    fun List<TruckResponseEntity>.toTruckResponseItem(): MutableList<TruckResponseItem> {
        val truckSchedules = ArrayList<TruckResponseItem>()
        forEach {
            truckSchedules.add(TruckResponseItem(it.driverName, it.imageURL, it.lastUpdated, it.lat,it.lng,it.location,it.plateNo))
        }

        return truckSchedules.toMutableList()
    }
}