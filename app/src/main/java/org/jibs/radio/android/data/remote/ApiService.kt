package org.jibs.radio.android.data.remote

import org.jibs.radio.android.data.remote.response.StationsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {
    @GET("stations/list-by-system-name")
    suspend fun getStations(
        @Query(value = "systemName") systemName: String = "STATIONS_TOP",
        @Query("count") count: Int = 100
    ): StationsResponse
}
