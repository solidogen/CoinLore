package com.spyrdonapps.coinlore.data.di

import com.spyrdonapps.coinlore.data.api.CurrencyApi
import com.spyrdonapps.coinlore.data.repo.CurrencyRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    factory { Network.currencyApi }
    factory { CurrencyRepository(get()) }
}

private object Network {

    private const val API_URL = "https://api.coinlore.com/api/"

    private val retrofit
        get() = Retrofit.Builder().baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    val currencyApi: CurrencyApi
        get() = retrofit.create(CurrencyApi::class.java)
}
