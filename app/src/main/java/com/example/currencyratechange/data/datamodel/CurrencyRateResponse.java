package com.example.currencyratechange.data.datamodel;

import com.google.gson.annotations.SerializedName;

public class CurrencyRateResponse {


        @SerializedName("base_code")
        public String base_code;
        @SerializedName("rates")
        public Rates rates;
        @SerializedName("result")
        public String result;

        public CurrencyRateResponse(String base_code, Rates rates,String result) {
            this.base_code = base_code;
            this.rates = rates;
            this.result = result;
        }



}
