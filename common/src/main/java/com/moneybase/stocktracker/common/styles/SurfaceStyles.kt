package com.moneybase.stocktracker.common.styles

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import com.moneybase.stocktracker.common.extensions.conditional
import com.moneybase.stocktracker.common.theme.smallPadding


/**
 * This would be the root composable for all the other composables in the system.
 * We can use it to provide a template for the entire system like screen padding,
 * background color etc.
 *
 * */
@Composable
fun Window(
  modifier: Modifier = Modifier,
  windowConfig: WindowConfig = WindowConfig(),
  content: @Composable ColumnScope.() -> Unit
) {
  Surface(modifier = modifier
    .conditional(windowConfig.shouldWrapContent) {
      wrapContentSize()
    }
    .conditional(!windowConfig.shouldWrapContent) {
      fillMaxSize()
    }, color = MaterialTheme.colorScheme.background
  ) {
    Column(
      modifier = Modifier
        .padding(windowConfig.contentPadding)
        .conditional(windowConfig.isScrollable) {
          verticalScroll(rememberScrollState())
        }
    ) {
      content(this)
    }
  }
}

@Immutable
data class WindowConfig(
  val isScrollable: Boolean = true, //Mark this false if we need to host a List in this window
  val shouldWrapContent: Boolean = false,
  val contentPadding: PaddingValues = smallPadding
)