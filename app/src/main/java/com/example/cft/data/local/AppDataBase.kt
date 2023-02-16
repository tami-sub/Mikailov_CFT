package com.example.cft.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cft.domain.entity.Card

@Database(entities = [Card::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun cardDao(): CardDAO
}