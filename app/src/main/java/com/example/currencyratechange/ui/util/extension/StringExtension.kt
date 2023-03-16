package com.example.currencyratechange.ui.util.extension

fun String.removeDotConvertToDouble(): Double? {
    val dotRemoved = if (this.isNotEmpty() && this.last() == '.') {
        this.removeRange(
            this.length - 1,
            this.length
        )
    } else {
        this
    }
    return dotRemoved.toDoubleOrNull()
}
