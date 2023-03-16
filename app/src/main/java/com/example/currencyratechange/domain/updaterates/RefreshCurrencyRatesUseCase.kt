package com.example.currencyratechange.domain.updaterates

import com.example.currencyratechange.domain.model.Currency
import com.example.currencyratechange.data.model.ResultData
import kotlinx.coroutines.flow.Flow


interface RefreshCurrencyRatesUseCase {
    fun refreshCurrencyRateFromAPI(): Flow<ResultData<List<Currency>>>
}
