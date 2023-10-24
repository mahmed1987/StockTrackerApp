package com.moneybase.stocktracker.ui

import android.content.res.Resources
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.moneybase.stocktracker.business.usecases.user.GetLoggedInUser
import com.moneybase.stocktracker.dtos.user.ViewUser
import kotlinx.coroutines.CoroutineScope


/**
 * The single source of truth for the current app state.
 *
 * This hosts all the state related information of the app for example
 * 1 ) Navigation State
 * 2 ) Logged In User
 * 3 ) Current Selected Language
 * 4 ) Current Country of access
 * 5 ) Running certain sync workers
 * 6 ) Snackbars . etc . etc
 * or any other piece of information that would be needed app wide .
 *
 * */
class AppState(
  val snackbarHostState: SnackbarHostState,
  val navController: NavHostController,
  val coroutineScope: CoroutineScope
) {
  val currentDestination: NavDestination?
    @Composable get() = navController
      .currentBackStackEntryAsState().value?.destination

  val currentUser = GetLoggedInUser()

}

@Composable
fun rememberAppState(
  snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
  navController: NavHostController = rememberNavController(),
  coroutineScope: CoroutineScope = rememberCoroutineScope(),
) =
  remember(snackbarHostState, navController, coroutineScope) {
    AppState(snackbarHostState, navController, coroutineScope)
  }


