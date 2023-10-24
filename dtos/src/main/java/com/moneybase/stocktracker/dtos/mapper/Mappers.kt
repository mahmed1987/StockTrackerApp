package com.moneybase.stocktracker.dtos.mapper

import com.moneybase.stocktracker.dtos.stock.NetworkStock
import com.moneybase.stocktracker.dtos.stock.NetworkStockData
import com.moneybase.stocktracker.dtos.stock.NetworkStockValue
import com.moneybase.stocktracker.dtos.stock.ViewStock
import com.moneybase.stocktracker.dtos.stock.ViewStockData
import com.moneybase.stocktracker.dtos.stock.ViewStockValue

object Mappers {
  fun NetworkStock.toView() =
    ViewStock(symbol = symbol, name = name, exchange = exchange, country = country, type = type)

  fun NetworkStockData.toView() =
    ViewStockData(symbol = meta?.symbol.orEmpty(), values = values?.map { it.toView() }.orEmpty())

  fun NetworkStockValue.toView() = ViewStockValue(datetime, open, high, low, close, volume)
}