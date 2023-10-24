package com.moneybase.stocktracker.dtos.stock

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ViewStock(
  val symbol: String,
  val name: String,
  val exchange: String,
  val country: String,
  val type: String
) : Parcelable {
  companion object {
    fun preview() = ViewStock(
      symbol = "GOOGL",
      name = "Alphabet Inc",
      exchange = "NASDAQ",
      country = "United States",
      type = "Common Stock"
    )
  }
}


