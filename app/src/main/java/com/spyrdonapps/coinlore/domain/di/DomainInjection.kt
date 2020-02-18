package com.spyrdonapps.coinlore.domain.di

import com.spyrdonapps.coinlore.domain.usecase.GetCurrenciesUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { GetCurrenciesUseCase(get()) }
}
