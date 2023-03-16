package com.example.currencyratechange.data.mapper

import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.data.network.model.currencylist.CurrencyRateNetwork
import com.example.currencyratechange.domain.model.Currency


interface ObjectMapper {
    fun mapToEntity(listCurrency: List<CurrencyRateNetwork>): List<CurrencyRateEntity>
    fun entityToModel(listCurrencyEntity: List<CurrencyRateEntity>): List<Currency>
}
