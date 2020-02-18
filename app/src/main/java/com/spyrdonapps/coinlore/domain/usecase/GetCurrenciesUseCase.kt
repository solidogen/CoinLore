package com.spyrdonapps.coinlore.domain.usecase

import com.spyrdonapps.coinlore.data.repo.CurrencyRepository
import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import com.spyrdonapps.coinlore.domain.model.toModel

class GetCurrenciesUseCase(private val currencyRepository: CurrencyRepository) {

    suspend fun execute(): List<CurrencyModel> = currencyRepository.getCurrencies().map { it.toModel() }
}
