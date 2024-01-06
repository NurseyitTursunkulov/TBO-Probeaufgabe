package com.example.tbo_probeaufgabe.data.remote.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonClass
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import java.io.IOException


/**
 * Created by nurseiit.tursunkulov on 03.01.2024
 * CoinHistoryApiModel
 */
@JsonClass(generateAdapter = true)
data class CoinHistoryApiModel(
    val prices: List<List<String>>,
    @Json(name = "market_caps")
    val marketCaps: List<List<String>>,
    @Json(name = "total_volumes")
    val totalVolumes: List<List<String>>
){
    fun toLocalModel(id: String): CoinHistoryLocalModel {
        return CoinHistoryLocalModel(id, prices, marketCaps, totalVolumes)
    }
}

// TypeConverter class
//class ListListStringAdapter : JsonAdapter<List<List<String>>?>() {
//
//    @Throws(IOException::class)
//    override fun fromJson(reader: JsonReader): List<List<String>>? {
//        if (reader.peek() == JsonReader.Token.NULL) {
//            reader.nextNull()
//            return null
//        }
//
//        val result = mutableListOf<List<String>>()
//        reader.beginArray()
//        while (reader.hasNext()) {
//            val innerList = mutableListOf<String>()
//            reader.beginArray()
//            while (reader.hasNext()) {
//                innerList.add(reader.nextString())
//            }
//            reader.endArray()
//            result.add(innerList)
//        }
//        reader.endArray()
//        return result
//    }
//
//    @Throws(IOException::class)
//    override fun toJson(writer: JsonWriter, value: List<List<String>>?) {
//        if (value == null) {
//            writer.nullValue()
//            return
//        }
//
//        writer.beginArray()
//        for (innerList in value) {
//            writer.beginArray()
//            for (item in innerList) {
//                writer.value(item)
//            }
//            writer.endArray()
//        }
//        writer.endArray()
//    }
//}
