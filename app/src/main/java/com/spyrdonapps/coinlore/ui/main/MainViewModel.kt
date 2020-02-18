package com.spyrdonapps.coinlore.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spyrdonapps.coinlore.domain.usecase.GetCurrenciesUseCase
import com.spyrdonapps.coinlore.utils.extensions.intervalLaunch
import timber.log.Timber

class MainViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase): ViewModel() {

    private 

    fun getCurrencies() {
        viewModelScope.intervalLaunch(GET_CURRENCIES_INTERVAL_MS) {
            val response = getCurrenciesUseCase.execute()
            Timber.d(response.toString())
        }
    }

    companion object {
        const val GET_CURRENCIES_INTERVAL_MS = 3000L // todo change to 30000L
    }
}
