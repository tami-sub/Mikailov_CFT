package com.example.utils

import javax.inject.Singleton

@Singleton
object Utils {
    const val BASE_URL = "https://lookup.binlist.net/"
    const val DATABASE_NAME = "card_database"
    fun separateCardNumber(number: String) = number.chunked(4).joinToString(" ")
    fun getYesOrNo(it: Boolean?) = if (it != null) {
        if (it) "YES" else "NO"
    } else "-"
}