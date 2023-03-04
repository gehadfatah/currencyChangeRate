package com.example.currencyratechange.data

import com.example.currencyratechange.data.datamodel.CurrencyRateNetwork
import com.example.currencyratechange.data.datamodel.CurrencyResponse

fun CurrencyResponse.toListOfRates(): List<CurrencyRateNetwork>? {
    return this.rates?.asMap()?.map {
        CurrencyRateNetwork(
            code = it.key,
            rate = it.value.asDouble
        )
    }
}