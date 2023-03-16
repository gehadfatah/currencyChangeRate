package com.example.currencyratechange.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.currencyratechange.data.db.converter.RoomConverter
import com.example.currencyratechange.data.db.dao.CurrencyRateDao
import com.example.currencyratechange.data.db.dao.CurrencyRateUpdateTimeDao
import com.example.currencyratechange.data.db.entity.CurrencyHistoryEntity
import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.data.db.entity.CurrencyRateUpdateTimeEntity

@Database(
    entities = [CurrencyRateEntity::class, CurrencyRateUpdateTimeEntity::class, CurrencyHistoryEntity::class],
    version = 1
)
@TypeConverters(RoomConverter::class)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun getCurrencyDao(): CurrencyRateDao
    abstract fun getCurrencyUpdateTime(): CurrencyRateUpdateTimeDao
}
