package com.example.currencyratechange.data.network

import com.example.currencyratechange.data.datamodel.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{currency}")
    fun getCurrencyByTo(@Path("currency") currency: String): Call<CurrencyResponse>
    @GET("{currency}")
    suspend fun getCurrencies(
        @Path("currency") currency: String
    ): com.example.currencyratechange.data.network.model.currencylist.CurrencyResponse
}