package com.spyrdonapps.coinlore.ui.main

import androidx.lifecycle.ViewModel
import com.spyrdonapps.coinlore.domain.GetCurrenciesUseCase

class MainViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase): ViewModel() {

    fun getCurrencies() = getCurrenciesUseCase.xD()
}
