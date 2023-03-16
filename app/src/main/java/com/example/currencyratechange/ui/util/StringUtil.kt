package com.example.currencyratechange.ui.util

import android.content.Context

import com.example.currencyratechange.R
import com.example.currencyratechange.data.model.ResultData
import javax.inject.Inject

class StringUtil @Inject constructor(val context: Context) {

    fun getUnknownErrorMsg(): ResultData.Failed {
        return ResultData.Failed(
            title = context.getString(R.string.oh_snap),
            message = context.getString(R.string.error)
        )
    }
}
