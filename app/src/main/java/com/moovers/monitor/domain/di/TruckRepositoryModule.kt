package com.moovers.monitor.domain.di


import com.moovers.monitor.data.repository.TruckMonitorRepositoryImpl
import com.moovers.monitor.domain.repository.TruckMonitorRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class TruckModule {

    @Binds
    abstract fun bindTruckRepository(truckMonitorRepositoryImpl: TruckMonitorRepositoryImpl): TruckMonitorRepository
}