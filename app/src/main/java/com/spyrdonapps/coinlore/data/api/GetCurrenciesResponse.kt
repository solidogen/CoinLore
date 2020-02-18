package com.spyrdonapps.coinlore.data.api

import com.spyrdonapps.coinlore.data.model.CurrencyDto

data class GetCurrenciesResponse(
    val data: List<CurrencyDto>
)

