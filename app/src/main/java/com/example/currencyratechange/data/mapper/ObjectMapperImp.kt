package com.example.currencyratechange.data.mapper

import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.data.network.model.currencylist.CurrencyRateNetwork
import com.example.currencyratechange.domain.model.Currency
import com.example.currencyratechange.ui.util.extension.toDBModel
import com.example.currencyratechange.ui.util.extension.toModel
import javax.inject.Inject

class ObjectMapperImp @Inject constructor() : ObjectMapper {

    override fun mapToEntity(listCurrency: List<CurrencyRateNetwork>): List<CurrencyRateEntity> {
        return listCurrency.map {
            it.toDBModel()
        }
    }

    override fun entityToModel(listCurrencyEntity: List<CurrencyRateEntity>): List<Currency> {
        return listCurrencyEntity.map {
            it.toModel()
        }
    }


}
