package com.example.currencyratechange.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.currencyratechange.data.db.entity.CurrencyRateEntity

@Dao
interface CurrencyRateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(list: List<CurrencyRateEntity>)

    @Query("select * from CurrencyRateEntity")
    suspend fun getCurrencyList(): List<CurrencyRateEntity>

    @Query("SELECT * FROM CurrencyRateEntity WHERE code=:currencyCode ")
    suspend fun getCurrencyRate(
        currencyCode: String,
    ): List<CurrencyRateEntity>

}
