package com.example.currencyratechange.di

import com.example.currencyratechange.data.mapper.ObjectMapper
import com.example.currencyratechange.data.mapper.ObjectMapperImp
import com.example.currencyratechange.data.repository.CurrencyDataRepository
import com.example.currencyratechange.data.repository.CurrencyDataRepositoryImp
import com.example.currencyratechange.data.db.dao.CurrencyRateDao
import com.example.currencyratechange.data.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Singleton
    @Provides
    fun providesDataRepository(
        currencyRateDAO: CurrencyRateDao,
        api: Api,
        networkDaoMapper: ObjectMapper
    ): CurrencyDataRepository =
        CurrencyDataRepositoryImp(
            currencyRateDao = currencyRateDAO,
            api,
            networkDaoMapper
        )

    @Singleton
    @Provides
    fun providesNetworkMapper(): ObjectMapper = ObjectMapperImp()
}
