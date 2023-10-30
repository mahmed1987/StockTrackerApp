package com.moneybase.stocktracker.common.theme

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MediumSpacer() {
  Spacer(modifier = Modifier.size(mediumUnit))
}

@Composable
fun LargeSpacer(count: Int = 1) {
  repeat(count) {
    Spacer(modifier = Modifier.size(largeUnit))
  }
}

@Composable
fun SmallSpacer() {
  Spacer(modifier = Modifier.size(smallUnit))
}

@Composable
fun xSmallSpacer() {
  Spacer(modifier = Modifier.size(xSmallUnit))
}

@Composable
fun ColumnScope.FillSpacer(
) {
  Spacer(modifier = Modifier.weight(1f))
}

@Composable
fun RowScope.FillSpacer(
) {
  Spacer(modifier = Modifier.weight(1f))
}


