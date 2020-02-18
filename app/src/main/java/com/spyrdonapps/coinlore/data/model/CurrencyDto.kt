package com.spyrdonapps.coinlore.data.model

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class CurrencyDto(
    val name: String,
    @SerializedName("percent_change_1h")
    val hourlyChangePercentage: Double,
    @SerializedName("percent_change_24h")
    val dailyChangePercentage: Double,
    @SerializedName("price_usd")
    val priceInUsd: BigDecimal,
    val symbol: String,
    @SerializedName("volume24")
    val volume: BigDecimal
)
