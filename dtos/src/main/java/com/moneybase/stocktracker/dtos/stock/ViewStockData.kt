package com.moneybase.stocktracker.dtos.stock

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewStockData(
  val symbol: String,
  val values: List<ViewStockValue>,
) : Parcelable {
  companion object {
    val stockValue = ViewStockValue(
      datetime = "2023-10-27 15:59:00",
      open = "168.09500",
      high = "168.34000",
      low = "168.06000",
      close = "168.28500",
      volume = "1243258"
    )

    fun preview() = ViewStockData(
      symbol = "GOOGL",
      values = listOf(stockValue)

    )
  }
}

@Parcelize
data class ViewStockValue(
  val datetime: String,
  val open: String,
  val high: String,
  val low: String,
  val close: String,
  val volume: String
) : Parcelable



