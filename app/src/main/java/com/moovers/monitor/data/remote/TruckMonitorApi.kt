package com.moovers.monitor.data.remote


import com.moovers.monitor.domain.model.TruckResponseItem
import retrofit2.http.GET

interface TruckMonitorApi {

    @GET("api/candidate")
    suspend fun getTruckDetails():MutableList<TruckResponseItem>
}