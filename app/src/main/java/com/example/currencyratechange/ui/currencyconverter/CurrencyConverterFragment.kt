package com.example.currencyratechange.ui.currencyconverter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.currencyratechange.data.model.ResultData
import com.example.currencyratechange.databinding.FragmentCurrencyConverterBinding
import com.example.currencyratechange.domain.model.Currency
import com.example.currencyratechange.domain.model.CurrencyConverterState
import com.example.currencyratechange.ui.base.BaseFragment
import com.example.currencyratechange.ui.util.extension.guardLet
import com.example.currencyratechange.ui.util.extension.hideKeyboard
import com.example.currencyratechange.ui.util.extension.toTwoDecimalWithComma
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class CurrencyConverterFragment : BaseFragment() {

    private val viewModel: CurrencyConverterViewModel by viewModels()
    private lateinit var binding: FragmentCurrencyConverterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return FragmentCurrencyConverterBinding.inflate(inflater, container, false).apply {
            binding = this
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        initBindings()
        addObservers()
        addViewListeners()
    }

    private fun addObservers() {
        viewModel.currencyList.observe(this.viewLifecycleOwner, currencyListObserver)
        viewModel.convertedCurrency.observe(this.viewLifecycleOwner, convertedCurrencyObserver)
        viewModel.lastFetchDateTime.observe(this.viewLifecycleOwner) {
            it?.let { time ->
                binding.lastUpdateDateTime = time
            }
        }
        viewModel.userSelectionState.observe(this.viewLifecycleOwner, userUiStateObserver)
    }

    private fun initBindings() {
        binding.currenciesList = ArrayList<Currency>()
        binding.lastUpdateDateTime = ""
        binding.viewModel = viewModel
    }

    private fun addViewListeners() {
        binding.etFrom.doAfterTextChanged {
            it?.let { editable ->
                if (editable.toString().isNotEmpty()) {
                    onCurrencyChangedEvent()
                }
            }
        }

        binding.dropdownFromCurrency.setOnItemClickListener { _, _, position, _ ->
            binding.etFrom.hideKeyboard()
            viewModel.onCurrencyCodeSelected(binding.dropdownFromCurrency.text.toString())
            if (position >= 0 && !binding.etFrom.text.isNullOrEmpty() && !binding.dropdownToCurrency.text.isNullOrEmpty()) {
                onCurrencyChangedEvent()
            }
        }

        binding.dropdownToCurrency.setOnItemClickListener { _, _, position, _ ->
            binding.etTo.hideKeyboard()
            viewModel.onCurrencyCodeSelected(binding.dropdownToCurrency.text.toString())
            if (position >= 0 && !binding.etFrom.text.isNullOrEmpty() && !binding.dropdownFromCurrency.text.isNullOrEmpty()) {
                onCurrencyChangedEvent()
            }
        }




    }

    private fun onCurrencyChangedEvent() {
        guardLet(
            binding.etFrom.text.toString(),
            binding.dropdownFromCurrency.text.toString(),
            binding.dropdownToCurrency.text.toString()
        ) {
            return
        }

        viewModel.onCurrencyInputChanged(
            binding.etFrom.text.toString(),
            binding.dropdownFromCurrency.text.toString(),
            binding.dropdownToCurrency.text.toString()
        )
    }

    private val currencyListObserver = Observer<ResultData<List<Currency>>> {
        when (it) {
            is ResultData.Loading -> {
                showProgressDialog()
            }

            is ResultData.Success -> {
                resetSelections()
                it.data?.let { _ ->
                    binding.currenciesList = it.data
                }
                hideProgressDialog()
            }

            is ResultData.Failed -> {
                hideProgressDialog()
                showError(it.title, msg = it.message, binding.parentLayout)
            }

            is ResultData.Exception -> {
                hideProgressDialog()
                showError(msg = it.msg, parent = binding.parentLayout)
            }

            else -> {
                hideProgressDialog()
                showError(parent = binding.parentLayout)
            }
        }
    }

    private val convertedCurrencyObserver = Observer<ResultData<Double>> {
        when (it) {
            is ResultData.Success -> {
                it.data?.let { converted ->
                    binding.etTo.setText(converted.toTwoDecimalWithComma())

                    binding.desctvRate.setText(" ${binding.etFrom.text.toString()} ${binding.dropdownFromCurrency.text.toString()} equals ${converted.toTwoDecimalWithComma()} ${binding.dropdownToCurrency.text.toString()}")
                }
            }

            is ResultData.Failed -> {
                showError(it.title, msg = it.message, binding.parentLayout)
            }

            is ResultData.Exception -> {
                showError(msg = it.msg, parent = binding.parentLayout)
            }

            is ResultData.Loading -> {
            }

            else -> {
                showError(parent = binding.parentLayout)
            }
        }
    }

    private val userUiStateObserver = Observer<CurrencyConverterState?> {
        binding.dropdownFromCurrency.setText(it.baseCurrency ?: "", false)
        binding.dropdownToCurrency.setText(it.toCurrency ?: "", false)
        binding.etFrom.setText(it.inputCurrency ?: "1")
    }

    private fun resetSelections() {
        binding.etFrom.setText("1")
        binding.etTo.setText("")
        binding.dropdownToCurrency.setText("")
        binding.dropdownFromCurrency.setText("")
    }

    override fun onStop() {
        super.onStop()
        viewModel.onStopEvent(
            inputCurrency = binding.etFrom.text.toString(),
            baseCurrencyCode = binding.dropdownFromCurrency.text.toString(),
            binding.dropdownToCurrency.text.toString(),
        )
    }
}
