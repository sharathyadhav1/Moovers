package com.moovers.monitor.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface TruckDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insertTruck(truckList: MutableList<TruckResponseEntity>)

    @Query("SELECT * FROM truck_data")
    fun getTruck(): MutableList<TruckResponseEntity>

}