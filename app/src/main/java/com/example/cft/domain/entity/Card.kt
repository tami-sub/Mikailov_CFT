package com.example.cft.domain.entity

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(
    @PrimaryKey var cardNumber: String = "",
    @Embedded val bank: Bank? = null,
    @ColumnInfo val brand: String? = null,
    @Embedded val country: Country? = null,
    @Embedded val number: Number? = null,
    @ColumnInfo val prepaid: Boolean? = null,
    @ColumnInfo val scheme: String? = null,
    @ColumnInfo val type: String? = null
)