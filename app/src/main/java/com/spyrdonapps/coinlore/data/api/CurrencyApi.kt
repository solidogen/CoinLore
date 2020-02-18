package com.spyrdonapps.coinlore.data.api

import retrofit2.http.GET

interface CurrencyApi {

    @GET("tickers/?limit=20")
    suspend fun getCurrencies(): GetCurrenciesResponse
}
