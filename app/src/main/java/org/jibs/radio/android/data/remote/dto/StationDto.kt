package org.jibs.radio.android.data.remote.dto

import org.jibs.radio.android.domain.model.Station

data class StationDto(
    val id: String?,
    val name: String?,
    val city: String?,
    val country: String?,
    val logo100x100: String?,
    val logo300x300: String?
){
    fun toDomain(): Station? {
        apply {
            return Station(
                id = id?: return null,
                name = name?: return null,
                city = city,
                country = country,
                logo = logo100x100,
                logoLarge = logo300x300,
            )
        }
    }
}