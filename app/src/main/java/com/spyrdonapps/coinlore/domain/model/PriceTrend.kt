package com.spyrdonapps.coinlore.domain.model

import java.math.BigDecimal

enum class PriceTrend {
    RISING,
    EQUAL,
    FALLING;

    companion object {

        fun calculateTrend(newPrice: BigDecimal, oldPrice: BigDecimal): PriceTrend =
            when {
                newPrice > oldPrice -> RISING
                newPrice < oldPrice -> FALLING
                else -> EQUAL
            }

    }
}
