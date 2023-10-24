package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.moneybase.stocktracker.common.styles.LargeBody
import com.moneybase.stocktracker.common.styles.LargeHeading
import com.moneybase.stocktracker.common.styles.SmallBody
import com.moneybase.stocktracker.common.styles.SmallHeading
import com.moneybase.stocktracker.common.styles.SmallTitle
import com.moneybase.stocktracker.common.styles.StockTrackerCard
import com.moneybase.stocktracker.common.theme.LargeSpacer
import com.moneybase.stocktracker.common.theme.SmallSpacer
import com.moneybase.stocktracker.common.theme.StockTrackerTheme
import com.moneybase.stocktracker.common.theme.smallPadding
import com.moneybase.stocktracker.dtos.stock.ViewStockData
import com.moneybase.stocktracker.stockslistfeature.R
import java.nio.charset.StandardCharsets

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDataBottomSheet(stockData: ViewStockData, onDismiss: () -> Unit) {
  val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
  ModalBottomSheet(
    onDismissRequest = onDismiss,
    sheetState = bottomSheetState,
  ) {
    StockDataBottomSheetContent(stockData)
  }
}

@Composable
private fun StockDataBottomSheetContent(stockData: ViewStockData) {
  Column(
    modifier = Modifier
      .padding(smallPadding)
      .fillMaxWidth(),
  ) {
    SmallBody(text = stringResource(id = R.string.auto_refresh))
    LargeSpacer()
    SmallBody(text = stringResource(id = R.string.symbol))
    LargeHeading(text = stockData.symbol)
    SmallSpacer()
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
      StockTrackerCard {
        SmallHeading(text = stringResource(id = R.string.open))
        SmallTitle(text = stockData.values.firstOrNull()?.open.toString())
      }
      StockTrackerCard {
        SmallHeading(text = stringResource(id = R.string.close))
        SmallTitle(text = stockData.values.firstOrNull()?.close.toString())
      }
    }
  }
  LargeSpacer()
}

@Preview
@Composable
fun PreviewStockDataBottomSheet() {
  StockTrackerTheme {
    StockDataBottomSheetContent(stockData = ViewStockData.preview())
  }
}