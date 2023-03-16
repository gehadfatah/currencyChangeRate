package com.example.currencyratechange.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyratechange.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CurrencyActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_rate)
    }
}
