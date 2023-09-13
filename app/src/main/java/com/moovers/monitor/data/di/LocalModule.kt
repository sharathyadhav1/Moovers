package com.moovers.monitor.data.di

import android.content.Context
import androidx.room.Room
import com.moovers.monitor.data.local.TruckDatabase
import com.moovers.monitor.data.local.TruckDetailsDao

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private const val DB_NAME = "TruckMonitor"

    @Provides
    @Singleton
    fun provideTruckDatabase(@ApplicationContext context: Context): TruckDatabase {
        return Room.databaseBuilder(
            context,
            TruckDatabase::class.java,
            DB_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideTruckDao(truckDatabase: TruckDatabase): TruckDetailsDao {
        return truckDatabase.truckDetailsDao()
    }
}