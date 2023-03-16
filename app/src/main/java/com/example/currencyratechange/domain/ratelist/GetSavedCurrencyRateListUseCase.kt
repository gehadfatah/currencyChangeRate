package com.example.currencyratechange.domain.ratelist

import com.example.currencyratechange.domain.model.Currency
import kotlinx.coroutines.flow.Flow

interface GetSavedCurrencyRateListUseCase {
    fun getSavedCurrencyList(): Flow<List<Currency>>
}
