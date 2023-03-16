package com.example.currencyratechange.data.repository

import com.example.currencyratechange.data.db.dao.CurrencyRateDao
import com.example.currencyratechange.data.db.entity.CurrencyRateEntity
import com.example.currencyratechange.data.mapper.ObjectMapper
import com.example.currencyratechange.data.model.ResultData
import com.example.currencyratechange.data.network.model.currencylist.toListOfRates
import com.example.currencyratechange.ui.util.extension.translateToError
import com.example.currencyratechange.data.network.Api
import com.example.currencyratechange.ui.util.AppConstant
import javax.inject.Inject
import javax.inject.Singleton



@Singleton
class CurrencyDataRepositoryImp @Inject constructor(
    private val currencyRateDao: CurrencyRateDao,
    private val currencyConverterAPI: Api,
    private val networkDaoMapper: ObjectMapper
) : CurrencyDataRepository {

    override suspend fun updateDataFromNetwork(): ResultData<List<CurrencyRateEntity>> {
        try {
            val response = currencyConverterAPI.getCurrencies(AppConstant.BASE_CURRENCY)
            if (response.result=="success") {
                val result = response.toListOfRates()
                result?.let {
                    val daoRates = networkDaoMapper.mapToEntity(it)
                    currencyRateDao.insert(daoRates)
                    return ResultData.Success(daoRates)
                } ?: run {
                    return ResultData.Failed(
                        title = "Oh Snap!",
                        message = "Server seems busy - Please try again later"
                    )
                }
            } else {
                return ResultData.Failed(
                    title = "API Error",
                    message = "Error code ${response.error?.code ?: " Reason - ${response.error}"},"
                )
            }
        } catch (ex: Exception) {
            return ex.translateToError()
        }
    }







    override suspend fun getCurrencyRateList(): List<CurrencyRateEntity> =
        currencyRateDao.getCurrencyList()



    override suspend fun getCurrencyRateList(currencyCode: String): List<CurrencyRateEntity>? =
        currencyRateDao.getCurrencyRate(currencyCode)

}
