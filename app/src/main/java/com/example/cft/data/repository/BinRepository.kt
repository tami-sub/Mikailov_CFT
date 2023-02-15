package com.example.cft.data.repository


import com.example.cft.data.network.BinApi
import com.example.cft.domain.entity.Card
import javax.inject.Inject

class BinRepository
@Inject constructor(private val api: BinApi) : IBinRepository {
    override suspend fun getCardInfo(number: String): Result<Card> {
        return api.getCardInfo(number)
    }
}