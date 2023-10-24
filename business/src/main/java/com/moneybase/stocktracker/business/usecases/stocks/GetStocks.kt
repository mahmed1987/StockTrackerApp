package com.moneybase.stocktracker.business.usecases.stocks

import com.moneybase.stocktracker.dtos.mapper.Mappers.toView
import com.moneybase.stocktracker.dtos.stock.ViewStock
import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.system.map
import com.moneybase.stocktracker.repositories.stocks.StocksDataSource

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetStocks @Inject constructor(
  private val stocksDataSource: StocksDataSource
) {
  suspend operator fun invoke(): Either<Failure, List<ViewStock>> {
    return stocksDataSource.getAllStocks().map { it.map { it.toView() } }
  }

}