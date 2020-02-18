package com.spyrdonapps.coinlore.domain.model

import com.spyrdonapps.coinlore.data.model.CurrencyDto
import java.math.BigDecimal

data class CurrencyModel(
    val name: String,
    val hourlyChangePercentage: String,
    val dailyChangePercentage: String,
    val priceInUsd: String,
    val symbol: String,
    val dailyTradeVolume: BigDecimal
)

fun CurrencyDto.toModel(): CurrencyModel {
    return CurrencyModel(
        name = name,
        hourlyChangePercentage = hourlyChangePercentage,
        dailyChangePercentage = dailyChangePercentage,
        priceInUsd = priceInUsd,
        symbol = symbol,
        dailyTradeVolume = volume
    )
}
