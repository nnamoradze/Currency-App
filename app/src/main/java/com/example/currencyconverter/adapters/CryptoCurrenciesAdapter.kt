package com.example.currencyconverter.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.currencyconverter.R
import com.example.currencyconverter.apiData.CryptoDataItem

class CryptoCurrenciesAdapter() :
    RecyclerView.Adapter<CryptoCurrenciesAdapter.CryptoCurrenciesViewHolder>() {

    private val cryptoList = mutableListOf<CryptoDataItem>()

    class CryptoCurrenciesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var cryptoCurrencyName: TextView = itemView.findViewById(R.id.cryptoCurrencyName)
        var cryptoCurrencyPrice: TextView = itemView.findViewById(R.id.cryptoCurrencyPrice)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCurrenciesViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.crypto_currencies_item, parent, false)
        return CryptoCurrenciesViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CryptoCurrenciesViewHolder, position: Int) {
        holder.cryptoCurrencyName.text = cryptoList[position].name
        holder.cryptoCurrencyPrice.text = cryptoList[position].currentPrice.toString()+"$"
    }

    override fun getItemCount(): Int {
        return cryptoList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: MutableList<CryptoDataItem>){

        this.cryptoList.clear()

        this.cryptoList.addAll(data)
        notifyDataSetChanged()

    }


}


