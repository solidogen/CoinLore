package com.spyrdonapps.coinlore.ui.main

import androidx.lifecycle.Observer
import com.spyrdonapps.coinlore.data.repo.CurrencyRepository
import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import com.spyrdonapps.coinlore.domain.usecase.GetCurrenciesUseCase
import com.spyrdonapps.coinlore.utils.state.Result
import io.mockk.mockk
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val mockedCurrencyRepository = mockk<CurrencyRepository>(relaxed = true)
val mockedLiveDataObserver = mockk<Observer<Result<List<CurrencyModel>>>>()

val testMainModule = module {
    viewModel {
        MainViewModel(
            GetCurrenciesUseCase(mockedCurrencyRepository)
        )
    }
}
