package com.example.currencyratechange.domain.ratelist

import com.example.currencyratechange.data.repository.CurrencyDataRepository
import com.example.currencyratechange.domain.model.Currency
import com.example.currencyratechange.data.mapper.ObjectMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSavedCurrencyRateListUseCaseImp @Inject constructor(
    private val repository: CurrencyDataRepository,
    private val layersObjectMapper: ObjectMapper
) : GetSavedCurrencyRateListUseCase {

    override fun getSavedCurrencyList(): Flow<List<Currency>> = flow {
        emit(layersObjectMapper.entityToModel(repository.getCurrencyRateList()))
    }
}
