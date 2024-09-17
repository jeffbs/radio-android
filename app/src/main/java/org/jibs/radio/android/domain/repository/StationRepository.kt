package org.jibs.radio.android.domain.repository

import kotlinx.coroutines.flow.Flow
import org.jibs.radio.android.domain.model.Station

interface StationRepository {
    fun getStations(): Flow<List<Station>>
    fun getStationById(id: Int): Flow<Station>
}