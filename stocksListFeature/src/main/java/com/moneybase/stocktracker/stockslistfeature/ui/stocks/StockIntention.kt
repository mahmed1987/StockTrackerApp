package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import com.moneybase.stocktracker.common.base.Intention
import java.io.File

sealed class StocksIntention : Intention {
  object GetStocks : StocksIntention()
  object DismissSelectedStock : StocksIntention()
  class GetStockData(val symbol: String) : StocksIntention() {
    override val repeatAfter: Long
      get() = 8000L
  }

  class FilterStocks(val query: String) : StocksIntention()
}






