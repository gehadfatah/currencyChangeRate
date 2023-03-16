package com.example.currencyratechange.domain.currrencyconverter

import com.example.currencyratechange.data.model.ResultData
import kotlinx.coroutines.flow.Flow

interface CurrencyConverterUseCase {
    suspend fun calculateCurrency(
        input: String,
        baseCurrency: String,
        targetCurrency: String
    ): Flow<ResultData<Double>>
}
