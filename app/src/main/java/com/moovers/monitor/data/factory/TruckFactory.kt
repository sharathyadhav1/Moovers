package com.moovers.monitor.data.factory


import com.moovers.monitor.data.source.TruckEntityData
import com.moovers.monitor.data.source.local.LocalTruckEntityData
import com.moovers.monitor.data.source.network.NetworkTruckEntityData
import com.moovers.monitor.util.Source
import javax.inject.Inject

class TruckFactory @Inject constructor(private val networkScheduleEntityData: NetworkTruckEntityData,
                                       private val localScheduleEntityData: LocalTruckEntityData
) {

    fun create(source: Source): TruckEntityData {
        return when (source) {
            Source.NETWORK -> networkScheduleEntityData
            else -> localScheduleEntityData
        }
    }
}