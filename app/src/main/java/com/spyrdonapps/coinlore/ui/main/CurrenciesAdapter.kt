package com.spyrdonapps.coinlore.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.spyrdonapps.coinlore.R
import com.spyrdonapps.coinlore.domain.model.CurrencyModel
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrenciesAdapter : ListAdapter<CurrencyModel, CurrenciesAdapter.ViewHolder>(ItemCallback()) {

    private lateinit var recyclerView: RecyclerView

    override fun onAttachedToRecyclerView(recycler: RecyclerView) {
        super.onAttachedToRecyclerView(recycler)
        recyclerView = recycler
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_currency, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(currency: CurrencyModel) {
            with(view) {
                // todo bind
                isoCodeTextView.text = currency.priceInUsd.toPlainString()
//                fullNameTextView.text = currency.fullName.capitalizeWords()
            }
        }
    }

    class ItemCallback : DiffUtil.ItemCallback<CurrencyModel>() {

        override fun areItemsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem.symbol == newItem.symbol
        }

        override fun areContentsTheSame(oldItem: CurrencyModel, newItem: CurrencyModel): Boolean {
            return oldItem == newItem
        }
    }
}
