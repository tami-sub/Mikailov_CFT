package com.example.cft.data.local

import androidx.room.*
import com.example.cft.domain.entity.Card

@Dao
interface CardDAO {
    @Query("SELECT * FROM card_table")
    fun getAllCards(): List<Card>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertCardInfo(card: Card)

    @Query("DELETE FROM card_table")
    fun deleteAll()
}