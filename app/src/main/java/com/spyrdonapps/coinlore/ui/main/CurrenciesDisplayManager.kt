package com.spyrdonapps.coinlore.ui.main

import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import java.util.Collections

class CurrenciesDisplayManager {

    val adapter = CurrenciesAdapter()

    var sortingMode = SortingMode.Name
        set(value) {
            if (field == value) return
            field = value
            setCurrencies(adapter.currentList)
        }

    fun setCurrencies(currencies: List<CurrencyModel>) {
        val sortedCurrencies = when (sortingMode) {
            SortingMode.Name -> currencies.sortedBy { it.name }
            SortingMode.DailyTradeVolume -> currencies.sortedBy { it.dailyTradeVolume }
            SortingMode.DailyPriceChange -> currencies.sortedBy { it.dailyChangePercentage }
        }
        adapter.submitList(Collections.synchronizedList(sortedCurrencies))
    }
}
