package com.moneybase.stocktracker.dtos.stock

import com.google.gson.annotations.SerializedName

data class NetworkStocks(val data: List<NetworkStock>)

data class NetworkStock(
  val symbol: String,
  val name: String,
  val currency: String,
  val exchange: String,
  @SerializedName("mic_code")
  val micCode: String,
  val country: String,
  val type: String
)



