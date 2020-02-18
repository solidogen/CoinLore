package com.spyrdonapps.coinlore.domain

import com.spyrdonapps.coinlore.data.CurrencyRepository

class GetCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {

    fun xD() = currencyRepository.xD()
}