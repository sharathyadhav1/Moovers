package com.moovers.monitor.data.repository


import com.moovers.monitor.data.factory.TruckFactory
import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import com.moovers.monitor.util.Source
import javax.inject.Inject

class TruckMonitorRepositoryImpl @Inject constructor( private val truckFactory: TruckFactory)
    : TruckMonitorRepository {
    override suspend fun fetchTruckDetails(): MutableList<TruckResponseItem> {
        return truckFactory.create(Source.LOCAL).getTruckDetails()
            .ifEmpty { cachedTruckDetails() }
    }

    override suspend fun cachedTruckDetails() : MutableList<TruckResponseItem>{
        return truckFactory.create(Source.NETWORK).getTruckDetails()
            .also { truckDetailsFromNetwork ->
                truckFactory.create(Source.LOCAL).addTruckDetails(truckDetailsFromNetwork)
            }
    }





}