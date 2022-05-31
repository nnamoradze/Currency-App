package com.example.currencyconverter.remote

import com.example.currencyconverter.apiInterfaces.ApiInterface
import com.example.currencyconverter.fragments.BASE_URL_CRYPTO
import com.example.currencyconverter.fragments.BASE_URL_EXCHANGE
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    val retrofit_crypto by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL_CRYPTO)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)
    }

    val retrofi_exchange by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL_EXCHANGE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiInterface::class.java)

    }


}