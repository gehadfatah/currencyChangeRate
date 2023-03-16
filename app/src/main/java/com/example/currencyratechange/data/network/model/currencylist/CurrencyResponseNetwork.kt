package com.example.currencyratechange.data.network.model.currencylist

import com.example.currencyratechange.data.network.model.CurrencyAPIError
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class CurrencyResponse(
    @SerializedName("result")
    @Expose
    val result: String? = "success",
    @SerializedName("timestamp")
    @Expose
    val timestamp: Long = 0L,
    @SerializedName("base")
    @Expose
    val base: String?,
    @SerializedName("date")
    @Expose
    val date: String?,
    @SerializedName("rates")
    @Expose
    val rates: JsonObject?,
    @SerializedName("error")
    @Expose
    val error: CurrencyAPIError?
)

fun CurrencyResponse.toListOfRates(): List<CurrencyRateNetwork>? {
    return this.rates?.asMap()?.map {
        CurrencyRateNetwork(
            code = it.key,
            rate = it.value.asDouble
        )
    }
}
