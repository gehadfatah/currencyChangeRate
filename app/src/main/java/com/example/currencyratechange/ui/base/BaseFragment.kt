package com.example.currencyratechange.ui.base

import android.view.View
import androidx.fragment.app.Fragment
import com.example.currencyratechange.ui.util.extension.showSnackBar
import com.example.currencyratechange.R


open class BaseFragment : Fragment() {


    protected fun showProgressDialog() {
        activity?.let {

        }
    }

    protected fun hideProgressDialog() {
    }

    protected fun showError(
        title: String? = context?.getString(R.string.error),
        msg: String? = context?.getString(R.string.oh_snap),
        parent: View
    ) {
        if (!title.isNullOrEmpty() && !msg.isNullOrEmpty()) {
            parent.showSnackBar(
                msg,
                title
            )
        }
    }
}
