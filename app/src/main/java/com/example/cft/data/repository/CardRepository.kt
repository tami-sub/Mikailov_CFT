package com.example.cft.data.repository

import com.example.cft.data.local.CardDAO
import com.example.cft.domain.entity.Card
import javax.inject.Inject

class CardRepository
@Inject constructor(
    private val dao: CardDAO,
) : ICardRepository {
    override fun saveCard(card: Card) = dao.insertCardInfo(card)
    override fun deleteAllCard() = dao.deleteAll()
    override fun getAllCards() = dao.getAllCards()
}