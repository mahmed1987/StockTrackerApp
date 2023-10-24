package com.moneybase.stocktracker.ui

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import com.moneybase.stocktracker.common.locals.ProvideLoggedInUser
import com.moneybase.stocktracker.common.destinations.Destination
import com.moneybase.stocktracker.common.styles.MediumTitle
import com.moneybase.stocktracker.common.theme.smallPadding
import com.moneybase.stocktracker.stockslistfeature.navigation.stockListScreen

/**
 * The parent composable of the application
 * */
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun StockTrackerApp(appState: AppState) {
  Scaffold(
    snackbarHost = { SnackbarHost(hostState = appState.snackbarHostState) },
    topBar = { TopAppBar(title = { MediumTitle(text = "Stonks") }) })
  { innerPadding ->
    ProvideLoggedInUser(user = appState.currentUser) {
      NavHost(
        modifier = Modifier
          .consumeWindowInsets(innerPadding)
          .padding(
            top = innerPadding.calculateTopPadding(),
            bottom = innerPadding.calculateBottomPadding(),
            start = smallPadding.calculateStartPadding(LocalLayoutDirection.current),
            end = smallPadding.calculateEndPadding(LocalLayoutDirection.current)
          ),
        navController = appState.navController,
        startDestination = Destination.StocksList.route
      ) {
        stockListScreen()
      }
    }
  }
}

@Preview
@Composable
fun PreviewStockTrackerApp() {

}