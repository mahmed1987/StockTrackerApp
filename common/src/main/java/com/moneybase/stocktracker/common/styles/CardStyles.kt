package com.moneybase.stocktracker.common.styles

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.moneybase.stocktracker.common.extensions.conditional
import com.moneybase.stocktracker.common.theme.cardDefaultElevation
import com.moneybase.stocktracker.common.theme.smallPadding


@OptIn(ExperimentalMaterial3Api::class)
@Composable
    /**
     * If type is PrimarySecondaryGradient and a modifier.weight is provided , this composable wont work correct .
     * Hence we created PrimarySecondaryGradientCard for the time being . No time to investigate.
     * */
fun StockTrackerCard(
  modifier: Modifier = Modifier,
  colorScheme: ColorScheme = ColorScheme.Primary,
  isElevated: Boolean = false,
  isOutlined: Boolean = false,
  contentPadding: PaddingValues = smallPadding,
  onClick: () -> Unit = {},
  content: @Composable ColumnScope.() -> Unit,
) {
  Card(
    modifier = modifier,
    onClick = onClick,
    colors = when (colorScheme) {
      ColorScheme.Primary -> StockTrackerCardDefaults.primaryCardColors()
      ColorScheme.PrimaryContainer -> StockTrackerCardDefaults.primaryContainerCardColors()
      ColorScheme.PrimarySecondaryGradient -> StockTrackerCardDefaults.primaryCardColors()
      ColorScheme.Secondary -> StockTrackerCardDefaults.secondaryCardColors()
      ColorScheme.SecondaryContainer -> StockTrackerCardDefaults.secondaryContainerCardColors()
      ColorScheme.Tertiary -> StockTrackerCardDefaults.tertiaryCardColors()
      ColorScheme.TertiaryContainer -> StockTrackerCardDefaults.tertiaryContainerCardColors()
      ColorScheme.Surface -> StockTrackerCardDefaults.surfaceCardColors()
      ColorScheme.SurfaceVariant -> StockTrackerCardDefaults.surfaceVariantCardColors()
    },
    border = StockTrackerCardDefaults.outlinedCardBorder().takeIf { isOutlined },
    elevation = StockTrackerCardDefaults.cardElevation(if (isElevated) cardDefaultElevation else 0.dp),
  ) {

    Column(
      modifier = modifier
        .conditional(colorScheme == ColorScheme.PrimarySecondaryGradient) {
          background(
            brush = Brush.linearGradient(
              listOf(
                MaterialTheme.colorScheme.primary,
                MaterialTheme.colorScheme.secondary
              )
            )
          )
        }
        .padding(contentPadding)
    ) {
      content()
    }
  }

}

enum class ColorScheme {
  Primary,
  PrimaryContainer,
  PrimarySecondaryGradient,
  Secondary,
  SecondaryContainer,
  Tertiary,
  TertiaryContainer,
  Surface,
  SurfaceVariant,
}


object StockTrackerCardDefaults {

  @Composable
  fun outlinedCardBorder(borderWidth: Dp = 3.dp) =
    BorderStroke(borderWidth, MaterialTheme.colorScheme.outline)

  @Composable
  fun primaryCardColors(
    containerColor: Color = MaterialTheme.colorScheme.primary,
    contentColor: Color = MaterialTheme.colorScheme.onPrimary,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun primaryContainerCardColors(
    containerColor: Color = MaterialTheme.colorScheme.primaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onPrimaryContainer,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )


  @Composable
  fun secondaryCardColors(
    containerColor: Color = MaterialTheme.colorScheme.secondary,
    contentColor: Color = MaterialTheme.colorScheme.onSecondary,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun surfaceCardColors(
    containerColor: Color = MaterialTheme.colorScheme.surface,
    contentColor: Color = MaterialTheme.colorScheme.onSurface,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun surfaceVariantCardColors(
    containerColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    contentColor: Color = MaterialTheme.colorScheme.onSurfaceVariant,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun secondaryContainerCardColors(
    containerColor: Color = MaterialTheme.colorScheme.secondaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onSecondaryContainer,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun tertiaryCardColors(
    containerColor: Color = MaterialTheme.colorScheme.tertiary,
    contentColor: Color = MaterialTheme.colorScheme.onTertiary,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun tertiaryContainerCardColors(
    containerColor: Color = MaterialTheme.colorScheme.tertiaryContainer,
    contentColor: Color = MaterialTheme.colorScheme.onTertiaryContainer,
  ) = CardDefaults.cardColors(
    containerColor = containerColor,
    contentColor = contentColor,
  )

  @Composable
  fun cardElevation(defaultElevation: Dp = 8.dp) =
    CardDefaults.cardElevation(defaultElevation = defaultElevation)
}




