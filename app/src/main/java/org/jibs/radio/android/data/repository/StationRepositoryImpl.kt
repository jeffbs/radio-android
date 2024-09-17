package  org.jibs.radio.android.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.jibs.radio.android.core.util.NetworkUtils
import org.jibs.radio.android.data.local.dao.StationDao
import org.jibs.radio.android.data.remote.ApiService
import org.jibs.radio.android.domain.model.Station
import org.jibs.radio.android.domain.repository.StationRepository
import timber.log.Timber
import java.io.IOException
import javax.inject.Inject


class StationRepositoryImpl @Inject constructor(
    private val stationDao: StationDao,
    private val apiService: ApiService,
    private val networkUtils: NetworkUtils
) : StationRepository {

    override fun getStations(): Flow<List<Station>> {
        return flow{
            emit(stationDao.getAllStations())

            if(isNetworkAvailable()){
                try {
                    val stations = fetchStationsRemote()
                    Timber.d("fetched stations: $stations")
                    stationDao.insertStations(stations)
                    emit(stationDao.getAllStations())
                } catch (exception: IOException){
                    // Network error
                }
            }
        }
    }

    override fun getStationById(id: Int): Flow<Station> {
        return stationDao.getStationById(id)
    }

    private suspend fun fetchStationsRemote(): List<Station> {
        val response = apiService.getStations()
        return response.playables.mapNotNull {
            Timber.d("station: $it")
            it.toDomain()
        }
    }

    private fun isNetworkAvailable(): Boolean {
        return networkUtils.isNetworkAvailable()
    }
}

