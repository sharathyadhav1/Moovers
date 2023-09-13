package com.moovers.monitor.data.mapper

import com.moovers.monitor.data.local.TruckResponseEntity
import com.moovers.monitor.domain.model.TruckResponseItem


object TruckResponseMapper {

    fun List<TruckResponseItem>.toTruckResponseEntity(): MutableList<TruckResponseEntity> {
        val praySchedules = ArrayList<TruckResponseEntity>()
        forEach {
            praySchedules.add(TruckResponseEntity(it.driverName, it.imageURL, it.lastUpdated, it.lat,it.lng,it.location,it.plateNo))
        }

        return praySchedules.toMutableList()
    }
}