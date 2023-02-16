package com.example.cft.data.repository

import com.example.cft.domain.entity.Card

interface ICardRepository {
    fun saveCard(card: Card)
    fun deleteAllCard()
    fun getAllCards(): List<Card>
}