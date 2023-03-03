package com.example.currencyratechange.data

import com.example.currencyratechange.data.datamodel.CurrencyRateResponse
import com.example.currencyratechange.data.datamodel.CurrencyResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("{currency}")
    /*suspend*/ fun getCurrencyByTo(@Path("currency") currency: String): Call<CurrencyResponse>

}