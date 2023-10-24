package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneybase.stocktracker.common.locals.LocalLoggedInUser
import com.moneybase.stocktracker.common.locals.ProvideLoggedInUser
import com.moneybase.stocktracker.common.styles.ColorScheme
import com.moneybase.stocktracker.common.styles.LargeBody
import com.moneybase.stocktracker.common.styles.LargeTitle
import com.moneybase.stocktracker.common.styles.MediumTitle
import com.moneybase.stocktracker.common.styles.SmallBody
import com.moneybase.stocktracker.common.styles.SmallTitle
import com.moneybase.stocktracker.common.styles.StockTrackerCard
import com.moneybase.stocktracker.common.theme.SmallSpacer
import com.moneybase.stocktracker.common.theme.StockTrackerIcons
import com.moneybase.stocktracker.common.theme.StockTrackerTheme
import com.moneybase.stocktracker.dtos.stock.ViewStock
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.user.ViewUser

@Composable
fun StocksListItem(stock: ViewStock, onItemSelected: (ViewStock) -> Unit) {
  StockTrackerCard(
    colorScheme = ColorScheme.SecondaryContainer,
    modifier = Modifier.clickable { onItemSelected(stock) }) {
    LargeTitle(text = stock.symbol)
    SmallSpacer()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
      SmallBody(text = stock.exchange)
      SmallBody(text = stock.type)
      SmallBody(text = stock.name)
    }
  }
}

@Preview
@Composable
fun PreviewStockListItem() {
  StockTrackerTheme {
    StocksListItem(stock = ViewStock.preview()) {}
  }
}