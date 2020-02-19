package com.spyrdonapps.coinlore.ui.main

import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import com.spyrdonapps.coinlore.domain.model.PriceTrend
import com.spyrdonapps.coinlore.domain.model.SortingMode
import java.util.Collections

class CurrenciesDisplayManager {

    private var lastCurrencies: List<CurrencyModel>? = null

    val adapter = CurrenciesAdapter()

    var sortingMode = SortingMode.Name
        set(value) {
            if (field == value) return
            field = value
            sortAndDisplayCurrencies(adapter.currentList)
        }

    fun setCurrencies(newCurrencies: List<CurrencyModel>) {
        lastCurrencies?.let { lastCurrencies ->
            addPriceTrends(newCurrencies, lastCurrencies)
        }
        sortAndDisplayCurrencies(newCurrencies)
        lastCurrencies = newCurrencies
    }

    private fun addPriceTrends(newCurrencies: List<CurrencyModel>, latestCurrencies: List<CurrencyModel>) {
        newCurrencies.map { newCurrency ->
            latestCurrencies
                .find { it.symbol == newCurrency.symbol }
                ?.let { lastCurrencyOfSymbol ->
                    newCurrency.priceTrend = PriceTrend.calculateTrend(newCurrency.priceInUsd, lastCurrencyOfSymbol.priceInUsd)
                }
        }
    }

    private fun sortAndDisplayCurrencies(currencies: List<CurrencyModel>) {
        val sortedCurrencies = when (sortingMode) {
            SortingMode.Name -> currencies.sortedBy { it.name }
            SortingMode.DailyTradeVolume -> currencies.sortedBy { it.dailyTradeVolume }
            SortingMode.DailyPriceChange -> currencies.sortedBy { it.dailyChangePercentage }
        }
        adapter.submitList(Collections.synchronizedList(sortedCurrencies))
    }
}
