package com.example.currencyratechange

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.currencyratechange.data.APIClient
import com.example.currencyratechange.data.Api

import com.example.currencyratechange.data.datamodel.CurrencyResponse
import com.example.currencyratechange.data.datamodel.Rates
import com.example.currencyratechange.data.toListOfRates
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    var apiInterface: Api? = null
    private val viewModel: CurrencyViewModel by viewModels()
    val values : Array<String> = arrayOf("USD", "AED",  "EUR", "RUB")
    var data = ArrayList<String>()
    var convert_from_spinner: Spinner? =null
    var convert_to_spinner: Spinner? =null
    var fromCurrency: TextView? =null
    var toCurrency: TextView? =null
    var currencyRateShowTv: TextView? =null
    var currencyResponse: CurrencyResponse?=null
    var currencyFrom="USD"
    var currencyto="AED"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
        callApiSet()

    }

    private fun callApiSet() {
        apiInterface = APIClient.getClient().create(Api::class.java)
        viewModel.getCurrencyResponse("USD")
        GlobalScope.launch {

            val call3= apiInterface?.getCurrencyByTo(currencyFrom)

            call3?.enqueue(object : Callback<CurrencyResponse?> {
                override fun onResponse(call: Call<CurrencyResponse?>?, response: Response<CurrencyResponse?>) {
                    currencyResponse = response.body()
                    //  val datumList: List<Rates> = userList.rates

                    //todo loop over rates object and retrieve all currency change and set hashmap

                    organizeCurrencyRate()
                    //for ()
                  // fromCurrency?.text=currencyResponse.rates.$from
                }

                override fun onFailure(call: Call<CurrencyResponse?>, t: Throwable?) {
                    call.cancel()
                }
            })
        }

    }

    private fun organizeCurrencyRate() {
        try {
          /*  val jsonObject: JSONObject =  JSONObject(currencyResponse ?. rates ?. list.toString())

            val map: MutableMap<String, String> = HashMap()
            val iter: Iterator<*> = jsonObject.keys()
            while (iter.hasNext()) {
                val key = iter.next() as String
                val value = jsonObject.getString(key)
                map[key] = value
                Log.d("keywithvalues", "print    $key   $value")
            }*/
            val result = currencyResponse?.toListOfRates()
             Toast.makeText(
          applicationContext,
          "${result?.size}", Toast.LENGTH_SHORT
      ).show()
        } catch (e: Exception) {
            Log.d("error", "**   $e")
        }

    }

    private fun showListinSpinner(departmentNoRealmList:ArrayList<Rates>) {
        //String array to store all the book names
        val items = arrayOfNulls<String>(departmentNoRealmList.size)

        //Traversing through the whole list to get all the names
        for (i in 0 until departmentNoRealmList.size) {
            //Storing names to string array
           // items[i] = departmentNoRealmList.get(i).getDepartmentName()
        }

        //Spinner spinner = (Spinner) findViewById(R.id.spinner1);
        val adapter: ArrayAdapter<String?>
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        //setting adapter to spinner
       // spinnerDepartments.setAdapter(adapter)
        //Creating an array adapter for list view
    }

    private fun initView() {

        convert_from_spinner= findViewById(R.id.spinnerFrom)
        convert_to_spinner= findViewById(R.id.spinnerTo)
        currencyRateShowTv= findViewById(R.id.currencyRateShowTv)
        fromCurrency= findViewById(R.id.fromCurrency)
        toCurrency= findViewById(R.id.toCurrency)
        convert_from_spinner?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)
        convert_to_spinner?.adapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, values)

        convert_from_spinner?.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    //todo call api again with choose  currency and update ui
                   currencyFrom= values[position]
                   callApiSet()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    TODO("Not yet implemented")
                }
            }


        convert_to_spinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currencyto= values[position]
               // callApiSet()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }
    }


}