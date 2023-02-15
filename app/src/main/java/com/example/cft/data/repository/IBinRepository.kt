package com.example.cft.data.repository

import com.example.cft.domain.entity.Card
import dagger.Binds

interface IBinRepository {
    suspend fun getCardInfo(number: String): Result<Card>
}
