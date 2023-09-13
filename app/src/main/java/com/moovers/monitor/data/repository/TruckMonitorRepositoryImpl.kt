package com.moovers.monitor.data.repository


import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import javax.inject.Inject

class TruckMonitorRepositoryImpl
    : TruckMonitorRepository {
    override suspend fun fetchTruckDetails(): MutableList<TruckResponseItem> {
        TODO("Not yet implemented")
    }

    override suspend fun cachedTruckDetails(): MutableList<TruckResponseItem> {
        TODO("Not yet implemented")
    }


}