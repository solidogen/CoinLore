package com.spyrdonapps.coinlore.ui.main

import com.spyrdonapps.coinlore.data.CurrencyApi
import com.spyrdonapps.coinlore.data.CurrencyRepository
import com.spyrdonapps.coinlore.domain.GetCurrenciesUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    factory { CurrencyApi() }
    factory { CurrencyRepository(get()) }
    factory { GetCurrenciesUseCase(get()) }
    viewModel { MainViewModel(get()) }
}
