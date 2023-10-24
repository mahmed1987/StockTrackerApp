package com.moneybase.stocktracker.stockslistfeature.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.moneybase.stocktracker.common.destinations.Destination
import com.moneybase.stocktracker.stockslistfeature.ui.stocks.StocksListRoute

fun NavGraphBuilder.stockListScreen() {
  composable(route = Destination.StocksList.route) {
    StocksListRoute()
  }
}