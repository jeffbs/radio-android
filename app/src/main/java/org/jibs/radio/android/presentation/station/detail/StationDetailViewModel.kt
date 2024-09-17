package org.jibs.radio.android.presentation.station.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.jibs.radio.android.core.util.CoroutineDispatchers
import org.jibs.radio.android.domain.model.Station
import org.jibs.radio.android.domain.repository.StationRepository
import javax.inject.Inject

@HiltViewModel
class StationDetailViewModel @Inject constructor(
    private val stationRepository: StationRepository,
    private val coroutineDispatchers: CoroutineDispatchers
) : ViewModel() {

    private val _station = MutableStateFlow<Station?>(null)
    val station: StateFlow<Station?> = _station

    fun requestStation(stationId: String) {
        viewModelScope.launch (coroutineDispatchers.io) {
            // TODO: load station detail from network and persist aggregated data
            stationRepository.getStations().collect { stations ->
                val station = stations.find { it.id == stationId }
                requireNotNull(station) // TODO: Add proper error handling
                _station.update { station }
            }
        }

    }
}