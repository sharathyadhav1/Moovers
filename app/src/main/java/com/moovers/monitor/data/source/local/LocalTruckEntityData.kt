package com.moovers.monitor.data.source.local


import com.moovers.monitor.data.local.TruckDetailsDao
import com.moovers.monitor.data.mapper.TruckEntityMapper.toTruckResponseItem
import com.moovers.monitor.data.mapper.TruckResponseMapper.toTruckResponseEntity
import com.moovers.monitor.data.source.TruckEntityData
import com.moovers.monitor.domain.model.TruckResponseItem
import javax.inject.Inject

class LocalTruckEntityData @Inject constructor(private val truckDetailsDao: TruckDetailsDao
) : TruckEntityData {


    override suspend fun getTruckDetails(): MutableList<TruckResponseItem> {
        return truckDetailsDao.getTruck().toTruckResponseItem()
    }

    override suspend fun addTruckDetails(truckResponse: MutableList<TruckResponseItem>) {
        val truckEntities = truckResponse.toTruckResponseEntity()
        truckDetailsDao.insertTruck(truckEntities)
    }


}