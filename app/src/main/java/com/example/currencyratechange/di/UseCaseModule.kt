package com.example.currencyratechange.di

import com.example.currencyratechange.data.repository.CurrencyDataRepository
import com.example.currencyratechange.data.mapper.ObjectMapper
import com.example.currencyratechange.domain.currrencyconverter.CurrencyConverterUseCase
import com.example.currencyratechange.domain.currrencyconverter.CurrencyConverterUseCaseImp
import com.example.currencyratechange.domain.ratelist.GetSavedCurrencyRateListUseCase
import com.example.currencyratechange.domain.ratelist.GetSavedCurrencyRateListUseCaseImp
import com.example.currencyratechange.domain.updaterates.RefreshCurrencyRatesUseCase
import com.example.currencyratechange.domain.updaterates.RefreshCurrencyRatesUseCaseImp
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Singleton
    @Provides
    fun providesFetchCurrencyRateUseCase(
        repository: CurrencyDataRepository,
        mapper: ObjectMapper
    ): RefreshCurrencyRatesUseCase =
        RefreshCurrencyRatesUseCaseImp(repository, mapper)

    @Singleton
    @Provides
    fun providesSavedCurrencyRateUseCase(
        repository: CurrencyDataRepository,
        mapper: ObjectMapper
    ): GetSavedCurrencyRateListUseCase =
        GetSavedCurrencyRateListUseCaseImp(repository, mapper)


    @Singleton
    @Provides
    fun providesCurrencyConversionUseCase(
        repository: CurrencyDataRepository
    ): CurrencyConverterUseCase =
        CurrencyConverterUseCaseImp(repository)


}


