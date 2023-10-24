package com.moneybase.stocktracker.networks.stocks

import com.moneybase.stocktracker.dtos.stock.NetworkStock
import com.moneybase.stocktracker.dtos.stock.NetworkStockData
import com.moneybase.stocktracker.dtos.stock.NetworkStocks
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface StocksWebServices {
  @GET("stocks")
  suspend fun getStocks(@Query("exchange") exchange: String = "NASDAQ"): Response<NetworkStocks>

  @GET("time_series")
  suspend fun getStockData(
    @Query("symbol") symbol: String,
    @Query("interval") interval: String = "1min"
  ): Response<NetworkStockData>

//  https://api.twelvedata.com/stocksss
  //https://api.twelvedata.com/time_series?symbol=AAPL&interval=1min&apikey=demo
}