package com.example.currencyratechange.domain.model

data class CurrencyConverterState(
    var baseCurrency: String?,
    var toCurrency: String?,
    var inputCurrency: String?
)
