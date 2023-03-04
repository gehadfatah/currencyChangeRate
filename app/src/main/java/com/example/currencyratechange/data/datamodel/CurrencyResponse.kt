package com.example.currencyratechange.data.datamodel

import com.google.gson.JsonObject

data class CurrencyResponse(
    val base_code: String,
    val rates: JsonObject?,
    val result: String
)