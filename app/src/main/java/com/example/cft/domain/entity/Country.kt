package com.example.cft.domain.entity

import androidx.room.ColumnInfo

data class Country(
    val alpha2: String? = null,
    val currency: String? = null,
    val emoji: String? = null,
    val latitude: String? = null,
    val longitude: String? = null,
    @ColumnInfo(name ="country_name")
    val name: String? = null,
    val numeric: String? = null
)