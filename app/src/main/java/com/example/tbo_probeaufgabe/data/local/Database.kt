package com.example.tbo_probeaufgabe.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.data.remote.model.CoinHistoryLocalModel
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

@Database(entities = [CoinApiModel::class, CoinHistoryLocalModel::class], version = 3)
@TypeConverters(ListListStringConverter::class)
abstract class Database : RoomDatabase() {

    companion object {
        const val DATABASE_NAME = "coin_database"
    }

    abstract fun getDao(): LocalDataSource
}

class ListListStringConverter {

    @TypeConverter
    fun fromStringListList(value: String?): List<List<String>>? {
        return value?.let {
            val type = Types.newParameterizedType(List::class.java, List::class.java, String::class.java)
            val adapter: JsonAdapter<List<List<String>>> = Moshi.Builder().build().adapter(type)
            return adapter.fromJson(value)
        }
    }

    @TypeConverter
    fun toStringListList(value: List<List<String>>?): String? {
        return value?.let {
            val type = Types.newParameterizedType(List::class.java, List::class.java, String::class.java)
            val adapter: JsonAdapter<List<List<String>>> = Moshi.Builder().build().adapter(type)
            return adapter.toJson(value)
        }
    }
}