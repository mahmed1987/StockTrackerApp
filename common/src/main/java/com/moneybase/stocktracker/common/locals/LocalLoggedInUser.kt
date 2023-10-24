package com.moneybase.stocktracker.common.locals

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.moneybase.stocktracker.dtos.user.ViewUser

val LocalLoggedInUser = compositionLocalOf<ViewUser> {
  error("No logged in user  provided")
}

@Composable
fun ProvideLoggedInUser(
  user: ViewUser,
  content: @Composable () -> Unit
) {
  CompositionLocalProvider(LocalLoggedInUser provides user, content = content)
}
