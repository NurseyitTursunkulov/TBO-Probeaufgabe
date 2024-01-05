package com.example.tbo_probeaufgabe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel

@Database(entities = [CoinApiModel::class], version = 1)
abstract class Database : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "coin_database"
    }

    abstract fun getDao(): Dao
}

