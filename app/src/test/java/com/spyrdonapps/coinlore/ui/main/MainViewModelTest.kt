package com.spyrdonapps.coinlore.ui.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.spyrdonapps.coinlore.data.model.CurrencyDto
import com.spyrdonapps.coinlore.domain.model.toModel
import com.spyrdonapps.coinlore.utils.state.Result
import io.mockk.clearAllMocks
import io.mockk.coEvery
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import java.math.BigDecimal

class MainViewModelTest : KoinTest {

    private val mainViewModel: MainViewModel by inject()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val currenciesDto = listOf(
        CurrencyDto("name", 0.23, 0.23, BigDecimal.ONE, "asd", BigDecimal.ONE),
        CurrencyDto("zname", 0.22, 0.44, BigDecimal.TEN, "zxc", BigDecimal.ONE)
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        startKoin {
            modules(testMainModule)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
        stopKoin()
        clearAllMocks()
    }

    @Test
    fun `when api returns success, livedata emits success result with correct data`() {
        runBlocking {
            coEvery { mockedCurrencyRepository.getCurrencies() } returns currenciesDto
            mainViewModel.currenciesLiveData.observeForever(mockedLiveDataObserver)
            verify { mockedLiveDataObserver.onChanged((Result.Success(currenciesDto.map { it.toModel() }))) }
        }
    }

    @Test
    fun `when api returns error, livedata emits error result with correct data`() {
        runBlocking {
            coEvery { mockedCurrencyRepository.getCurrencies() } throws Exception()
            mainViewModel.currenciesLiveData.observeForever(mockedLiveDataObserver)
            verify { mockedLiveDataObserver.onChanged((Result.Error())) }
        }
    }
}
