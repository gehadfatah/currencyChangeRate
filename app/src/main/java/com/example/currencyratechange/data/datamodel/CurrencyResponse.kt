package com.example.currencyratechange.data.datamodel

data class CurrencyResponse(
    val base_code: String,
    val rates: Rates,
    val result: String
)