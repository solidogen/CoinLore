package com.spyrdonapps.coinlore.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import com.spyrdonapps.coinlore.domain.usecase.GetCurrenciesUseCase
import com.spyrdonapps.coinlore.utils.extensions.intervalLaunch
import timber.log.Timber
import com.spyrdonapps.coinlore.utils.state.Result

class MainViewModel(private val getCurrenciesUseCase: GetCurrenciesUseCase): ViewModel() {

    // TODO unit tests of VM

    private val currenciesMutableLiveData = MutableLiveData<Result<List<CurrencyModel>>>()
    val currenciesLiveData: LiveData<Result<List<CurrencyModel>>> = currenciesMutableLiveData

    init {
        getCurrencies()
    }

    private fun getCurrencies() {
        currenciesMutableLiveData.postValue(Result.Loading)

        viewModelScope.intervalLaunch(GET_CURRENCIES_INTERVAL_MS) {
            try {
                getCurrenciesUseCase.execute().let { currenciesMutableLiveData.postValue(Result.Success(it)) }
            } catch (e: Exception) {
                Timber.e(e)
                currenciesMutableLiveData.postValue(Result.Error())
            }
        }
    }

    companion object {
        const val GET_CURRENCIES_INTERVAL_MS = 3000L
    }
}
