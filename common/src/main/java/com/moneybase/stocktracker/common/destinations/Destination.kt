package com.moneybase.stocktracker.common.destinations

sealed class Destination(val route: String) {
  object StocksList : Destination("stocks")
  object Settings : Destination("settings")
  object Profile : Destination("profile")
}