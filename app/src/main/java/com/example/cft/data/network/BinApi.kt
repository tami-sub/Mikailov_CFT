package com.example.cft.data.network

import com.example.cft.domain.entity.Card
import retrofit2.http.GET
import retrofit2.http.Path

interface BinApi {
    @GET("{number}")
    suspend fun getCardInfo(
        @Path("number") number: String,
    ): Result<Card>
}