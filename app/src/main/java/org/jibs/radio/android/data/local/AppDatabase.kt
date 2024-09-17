package org.jibs.radio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import org.jibs.radio.android.data.local.dao.StationDao
import org.jibs.radio.android.domain.model.Station

@Database(entities = [Station::class], exportSchema = false, version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun stationDao(): StationDao
}