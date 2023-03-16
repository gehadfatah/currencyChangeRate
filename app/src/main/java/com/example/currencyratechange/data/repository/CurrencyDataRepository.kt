package com.example.currencyratechange.data.repository

import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.data.model.ResultData
import java.util.*

interface CurrencyDataRepository {
    suspend fun updateDataFromNetwork(): ResultData<List<CurrencyRateEntity>>
    suspend fun getCurrencyRateList(): List<CurrencyRateEntity>
    suspend fun getCurrencyRateList(currencyCode: String): List<CurrencyRateEntity>?
}
