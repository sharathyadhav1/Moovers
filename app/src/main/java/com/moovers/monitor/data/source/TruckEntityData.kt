package com.moovers.monitor.data.source

import com.moovers.monitor.domain.model.TruckResponseItem


interface TruckEntityData {

    suspend fun getTruckDetails(): MutableList<TruckResponseItem>

    suspend fun addTruckDetails(praySchedules: MutableList<TruckResponseItem>)
}