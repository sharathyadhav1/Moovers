package com.moovers.monitor.data.source.network

import com.moovers.monitor.data.remote.TruckMonitorApi
import com.moovers.monitor.data.source.TruckEntityData
import com.moovers.monitor.domain.model.TruckResponseItem

import javax.inject.Inject

class NetworkTruckEntityData @Inject constructor(
    private val api: TruckMonitorApi
) : TruckEntityData {


    override suspend fun getTruckDetails(): MutableList<TruckResponseItem> {
        return api.getTruckDetails()

    }

    override suspend fun addTruckDetails(praySchedules: MutableList<TruckResponseItem>) {
        TODO("Not yet implemented")
    }

}