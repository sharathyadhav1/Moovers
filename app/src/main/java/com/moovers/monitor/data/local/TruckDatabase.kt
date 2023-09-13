package com.moovers.monitor.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(
    entities = [TruckResponseEntity::class],
    version = 1,
    exportSchema = true
)
abstract class TruckDatabase : RoomDatabase() {
    abstract fun truckDetailsDao(): TruckDetailsDao
}