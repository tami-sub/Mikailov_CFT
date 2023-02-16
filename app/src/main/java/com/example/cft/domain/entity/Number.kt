package com.example.cft.domain.entity

import androidx.room.PrimaryKey


data class Number(
    val length: String? = null,
    val luhn: Boolean? = null
)