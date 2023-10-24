package com.moneybase.stocktracker.repositories.stocks

import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.stock.NetworkStock
import com.moneybase.stocktracker.dtos.stock.NetworkStockData

interface StocksDataSource {
  suspend fun getAllStocks(): Either<Failure, List<NetworkStock>>
  suspend fun getStockData(symbol: String): Either<Failure, NetworkStockData>
}