package com.example.currencyratechange.ui.util.extension

import com.example.currencyratechange.data.network.model.currencylist.CurrencyRateNetwork
import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.domain.model.Currency

inline fun <T : Any> guardLet(vararg elements: T?, closure: () -> Nothing): List<T> {
    return if (elements.all { it != null }) {
        elements.filterNotNull()
    } else {
        closure()
    }
}


fun CurrencyRateEntity.toModel() = Currency(
    code = code, rate = rate
)

fun CurrencyRateNetwork.toDBModel() = CurrencyRateEntity(
    code = code ?: "", rate = rate
)


