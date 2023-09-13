package com.moovers.monitor.domain.repository

import com.moovers.monitor.domain.model.TruckResponseItem


interface TruckMonitorRepository {

    suspend fun fetchTruckDetails() : MutableList<TruckResponseItem>

    suspend fun cachedTruckDetails(): MutableList<TruckResponseItem>
}