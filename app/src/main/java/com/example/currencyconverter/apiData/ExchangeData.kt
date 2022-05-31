package com.example.currencyconverter.apiData


import com.google.gson.annotations.SerializedName

data class ExchangeData(
    @SerializedName("amount")
    val amount: Int,
    @SerializedName("from")
    val from: String,
    @SerializedName("to")
    val to: String,
    @SerializedName("value")
    val value: Double
)