package org.jibs.radio.android.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stations")
data class Station(
    @PrimaryKey val id: String,
    val name: String,
    val city: String?,
    val country: String?,
    val logo: String?
)