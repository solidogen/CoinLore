package com.spyrdonapps.coinlore.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.spyrdonapps.coinlore.R
import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import com.spyrdonapps.coinlore.domain.model.SortingMode
import com.spyrdonapps.coinlore.utils.RecyclerLifecycleUtils
import com.spyrdonapps.coinlore.utils.state.Result
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val viewModel: MainViewModel by viewModel()
    private val recyclerLifecycleUtils = RecyclerLifecycleUtils()
    private val currenciesDisplayManager = CurrenciesDisplayManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupToolbar()
        setupRecyclerView()
        observeViewModel()
        recyclerLifecycleUtils.handleSavedInstanceStateIfNeeded(savedInstanceState)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        recyclerLifecycleUtils.saveRecyclerState(outState, recyclerView)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.sort_by_name -> { currenciesDisplayManager.sortingMode = SortingMode.Name; true }
            R.id.sort_by_price_change -> { currenciesDisplayManager.sortingMode = SortingMode.DailyPriceChange; true }
            R.id.sort_by_trade_volume -> { currenciesDisplayManager.sortingMode = SortingMode.DailyTradeVolume; true }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun setupToolbar() {
        supportActionBar?.setTitle(R.string.toolbar_text)
    }

    private fun setupRecyclerView() {
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = currenciesDisplayManager.adapter
        }
    }

    private fun observeViewModel() {
        with(viewModel) {
            currenciesLiveData.observe(this@MainActivity) { handleGetCurrenciesResult(it) }
        }
    }

    private fun handleGetCurrenciesResult(result: Result<List<CurrencyModel>>) {
        when (result) {
            is Result.Success -> showCurrencies(result.data)
            Result.Loading -> showLoading()
            is Result.Error -> showError()
        }
    }

    private fun showCurrencies(currencies: List<CurrencyModel>) {
        progressBar.isVisible = false
        syncOffImageView.isVisible = false
        recyclerView.isVisible = true

        currenciesDisplayManager.setCurrencies(currencies)
        recyclerLifecycleUtils.restoreRecyclerState(recyclerView)
    }

    private fun showLoading() {
        progressBar.isVisible = true
        syncOffImageView.isVisible = false
        recyclerView.isVisible = false
    }

    private fun showError() {
        progressBar.isVisible = false
        syncOffImageView.isVisible = true
        recyclerView.isVisible = false
    }
}
