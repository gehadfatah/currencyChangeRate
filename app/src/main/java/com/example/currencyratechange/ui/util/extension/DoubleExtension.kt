package com.example.currencyratechange.ui.util.extension

import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import java.util.*

fun Double.toTwoDecimalWithComma(): String {
    val decimalFormat = DecimalFormat("#,###.##")
    decimalFormat.decimalFormatSymbols = DecimalFormatSymbols(Locale.getDefault())
    val bigDecimal = this.toBigDecimal().setScale(2, RoundingMode.HALF_UP)
    return decimalFormat.format(bigDecimal)
}

