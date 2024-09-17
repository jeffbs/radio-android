package org.jibs.radio.android.data.remote.response

import org.jibs.radio.android.data.remote.dto.StationDto

data class StationsResponse (
    val playables: List<StationDto>
)