package com.moovers.monitor.domain.usecase


import com.moovers.monitor.domain.model.TruckResponseItem
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import javax.inject.Inject

class GetTruckUseCase @Inject constructor(private val repository: TruckMonitorRepository) {

    suspend fun execute(): MutableList<TruckResponseItem>{
        return repository.fetchTruckDetails()
    }

}