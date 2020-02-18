package com.spyrdonapps.coinlore.data.repo

import com.spyrdonapps.coinlore.data.api.CurrencyApi
import com.spyrdonapps.coinlore.data.model.CurrencyDto

class CurrencyRepository(private val currencyApi: CurrencyApi) {

    suspend fun getCurrencies(): List<CurrencyDto> = currencyApi.getCurrencies().data
}
