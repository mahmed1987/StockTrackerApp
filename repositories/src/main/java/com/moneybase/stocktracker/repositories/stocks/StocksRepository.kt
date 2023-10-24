package com.moneybase.stocktracker.repositories.stocks

import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.stock.NetworkStock
import com.moneybase.stocktracker.dtos.stock.NetworkStockData
import com.moneybase.stocktracker.dtos.system.map
import com.moneybase.stocktracker.networks.networkCall
import com.moneybase.stocktracker.networks.stocks.StocksWebServices
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class StocksRepository @Inject constructor(private val webServices: StocksWebServices) :
  StocksDataSource {
  override suspend fun getAllStocks(): Either<Failure, List<NetworkStock>> =
    /** This api is returning nearly 100K results and there appears to be no way to supply an argument for paging
     *  I am just gonna be using the first 100 results from the response in the interest of local memory
     * */

    networkCall { webServices.getStocks() }.map { it.data }

  override suspend fun getStockData(symbol: String): Either<Failure, NetworkStockData> {
    return networkCall { webServices.getStockData(symbol) }.map { it }
  }
}