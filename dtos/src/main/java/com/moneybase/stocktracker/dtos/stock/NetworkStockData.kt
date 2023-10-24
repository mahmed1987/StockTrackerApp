package com.moneybase.stocktracker.dtos.stock

data class NetworkStockData(
  val meta: NetworkMeta?,
  val values: List<NetworkStockValue>?
)

data class NetworkMeta(
  val symbol: String,
  val interval: String,
  val currency: String,
  val exchange_timezone: String,
  val exchange: String,
  val mic_code: String,
  val type: String
)

data class NetworkStockValue(
  val datetime: String,
  val open: String,
  val high: String,
  val low: String,
  val close: String,
  val volume: String
)
