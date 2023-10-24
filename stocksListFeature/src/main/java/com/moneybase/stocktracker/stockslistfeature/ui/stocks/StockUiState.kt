package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.moneybase.stocktracker.common.base.UiState
import com.moneybase.stocktracker.dtos.stock.ViewStock
import com.moneybase.stocktracker.dtos.stock.ViewStockData
import kotlinx.android.parcel.Parcelize

@Parcelize
@Immutable
data class StockUiState(
  val stocks: List<ViewStock> = emptyList(),
  val filteredStocks: List<ViewStock> = emptyList(),
  val searchQuery: String = "",
  val selectedStock: ViewStockData? = null
) : UiState(), Parcelable {
  companion object {
    private val stockList = listOf(
      ViewStock(
        symbol = "AAPL",
        name = "Apple Inc",
        exchange = "NASDAQ",
        country = "United States",
        type = "Common Stock"
      ),
      ViewStock(
        symbol = "GOOGL",
        name = "Alphabet Inc",
        exchange = "NASDAQ",
        country = "United States",
        type = "Common Stock"
      ),
      ViewStock(
        symbol = "MSFT",
        name = "Microsoft Corporation",
        exchange = "NASDAQ",
        country = "United States",
        type = "Common Stock"
      )
    )

    fun preview() =
      StockUiState(
        stocks = stockList
      )
  }
}