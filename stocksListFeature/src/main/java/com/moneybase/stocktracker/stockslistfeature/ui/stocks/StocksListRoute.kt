package com.moneybase.stocktracker.stockslistfeature.ui.stocks

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.moneybase.stocktracker.common.locals.LocalLoggedInUser
import com.moneybase.stocktracker.common.locals.ProvideLoggedInUser
import com.moneybase.stocktracker.common.styles.ColorScheme
import com.moneybase.stocktracker.common.styles.LargeTitle
import com.moneybase.stocktracker.common.styles.MediumTitle
import com.moneybase.stocktracker.common.styles.StockTrackerCard
import com.moneybase.stocktracker.common.theme.MediumSpacer
import com.moneybase.stocktracker.common.theme.SmallSpacer
import com.moneybase.stocktracker.common.theme.StockTrackerIcons
import com.moneybase.stocktracker.common.theme.StockTrackerTheme
import com.moneybase.stocktracker.dtos.system.Failure
import com.moneybase.stocktracker.dtos.user.ViewUser

@Composable
fun StocksListRoute() {
//  TODO("sHOW LOADER WHEN CALL IS ACTIVE." +
//      "Test cases "
//  )
  val viewModel = hiltViewModel<StocksViewModel>()
  val stocksUiState by viewModel.uiState.collectAsStateWithLifecycle()
  val loading by viewModel.loading.collectAsState(false)
  val failure by viewModel.failure.collectAsStateWithLifecycle(Failure.NoFailure)
  var searchQuery by remember { mutableStateOf("") }

  Column {
    if (loading) {
      LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }
    SmallSpacer()
    StockListScreen(
      stocksUiState,
      searchQuery,
      onStockSelected = { viewModel.executeIntention(StocksIntention.GetStockData(it)) },
      onSearchQueryChanged = {
        viewModel.executeIntention(StocksIntention.FilterStocks(it))
        searchQuery = it
      },
      onDismissSelectedStock = { viewModel.executeIntention(StocksIntention.DismissSelectedStock) })
  }

  LaunchedEffect(key1 = viewModel) {
    //TODO Can I delay this LaunchedEffect until Lifecycle state is started?
    viewModel.executeIntention(StocksIntention.GetStocks)
  }
}

@Composable
fun StockListScreen(
  stocksUiState: StockUiState,
  searchQuery: String,
  onStockSelected: (String) -> Unit,
  onSearchQueryChanged: (String) -> Unit,
  onDismissSelectedStock: () -> Unit
) {
  Column {
    Row {
      StockTrackerCard(colorScheme = ColorScheme.Primary) {
        MediumTitle(text = "Hey there")
      }
      SmallSpacer()
      StockTrackerCard(colorScheme = ColorScheme.Secondary) {
        Icon(StockTrackerIcons.UserAvatar, contentDescription = "")
        LargeTitle(text = LocalLoggedInUser.current.name)
      }
    }
    MediumSpacer()
    TextField(
      modifier = Modifier.fillMaxWidth(),
      value = searchQuery,
      onValueChange = onSearchQueryChanged
    )
    SmallSpacer()
    LazyColumn {
      items(stocksUiState.filteredStocks) {
        StocksListItem(stock = it, onItemSelected = { onStockSelected(it.symbol) })
        SmallSpacer()
      }
    }
  }

  stocksUiState.selectedStock?.let {
    StockDataBottomSheet(it) {
      onDismissSelectedStock()
    }
  }
}

@Preview
@Composable
fun PreviewStocksListScreen() {
  val sampleUser = ViewUser(id = "1", "John Doe")
  StockTrackerTheme {
    ProvideLoggedInUser(user = sampleUser) {
      StockListScreen(StockUiState.preview(), "", {},{}) {}
    }
  }
}