package com.example.currencyconverter.apiInterfaces

import com.example.currencyconverter.apiData.CryptoDataItem
import com.example.currencyconverter.apiData.ExchangeData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.Query


interface ApiInterface {

    @GET("/api/v3/coins/markets?vs_currency=usd&order=market_cap_desc&per_page=50&page=1&sparkline=false/")
    suspend fun getCryptoData(): Response<List<CryptoDataItem>>

    @Headers("apikey: I3Rz8QXBVPofKtucsenkW7g2n7aDwqBg")
    @GET("v1/exchange-rates/nbg/convert")
    suspend fun getExchangeData(
        @Query("amount") Amount: Int,
        @Query("from") From: String,
        @Query("to") To: String
    ): Response<ExchangeData>


}