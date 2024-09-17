package org.jibs.radio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.jibs.radio.android.domain.model.Station

@Dao
interface StationDao {
    @Query("SELECT * FROM stations")
    fun getAllStationsFlow(): Flow<List<Station>>

    @Query("SELECT * FROM stations")
    fun getAllStations(): List<Station>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStations(stations: List<Station>)

    @Query("SELECT * FROM stations WHERE id=:id ")
    fun getStationById(id: Int): Flow<Station>
}