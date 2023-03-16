package com.example.currencyratechange.data.db.converter

import androidx.room.TypeConverter
import java.util.*

class RoomConverter {
    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return value?.let { Date(it) }
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time?.toLong()
    }
}
