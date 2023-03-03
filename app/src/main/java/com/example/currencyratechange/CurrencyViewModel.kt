package com.example.currencyratechange

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.currencyratechange.data.datamodel.CurrencyResponse

class CurrencyViewModel :ViewModel() {


    val liveData
        get() = _liveData
    private val _liveData = MutableLiveData<CurrencyResponse>()
    fun getCurrencyResponse(currency:String) {

    }

}