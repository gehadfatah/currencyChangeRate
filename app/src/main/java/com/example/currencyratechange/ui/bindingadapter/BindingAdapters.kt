package com.example.currencyratechange.ui.bindingadapter

import android.R
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.databinding.BaseObservable
import androidx.databinding.BindingAdapter
import com.example.currencyratechange.domain.model.Currency

object BindingAdapters : BaseObservable() {

    @BindingAdapter("loadCurrencies")
    @JvmStatic
    fun loadCurrencies(txt: AutoCompleteTextView, currencyCode: List<Currency>?) {
        currencyCode?.map { it.code }?.let {
            txt.setAdapter(
                ArrayAdapter(
                    txt.context,
                    R.layout.simple_list_item_activated_1,
                    it
                )
            )
        }
    }


}
