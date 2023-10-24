package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import androidx.lifecycle.SavedStateHandle
import com.moneybase.stocktracker.business.usecases.stocks.FilterStocks
import com.moneybase.stocktracker.business.usecases.stocks.GetStockData
import com.moneybase.stocktracker.business.usecases.stocks.GetStocks
import com.moneybase.stocktracker.common.base.BaseViewModel
import com.moneybase.stocktracker.common.base.toRight
import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.system.map
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
  private val getStocks: GetStocks,
  private val getStockData: GetStockData,
  private val filterStocks: FilterStocks
) :
  BaseViewModel<StocksIntention, StockUiState>() {
  override suspend fun reduce(
    uiState: StockUiState,
    intention: StocksIntention
  ): Either<Failure, StockUiState> {
    return when (intention) {
      StocksIntention.GetStocks -> {
        getStocks().map {
          uiState.copy(stocks = it, filteredStocks = it)
        }
      }

      is StocksIntention.GetStockData -> getStockData(intention.symbol).map {
        uiState.copy(selectedStock = it)
      }

      is StocksIntention.FilterStocks -> filterStocks(intention.query, uiState.stocks)
        .map { uiState.copy(filteredStocks = it, searchQuery = intention.query) }

      StocksIntention.DismissSelectedStock -> uiState.copy(selectedStock = null).toRight()
    }
  }

  override fun initializeState(): StockUiState = StockUiState()


}