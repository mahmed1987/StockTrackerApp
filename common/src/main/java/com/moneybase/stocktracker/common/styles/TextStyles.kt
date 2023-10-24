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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.moneybase.stocktracker.common.extensions.conditional
import com.moneybase.stocktracker.common.theme.smallPadding


/**
 * The single source of truth for all Text Composables in the system.
 * There will be a number of public interfaces for the main composable below
 * like [LargeHeading] , [MediumHeading], [SmallHeading],[LargeTitle] , [MediumTitle], [SmallTitle] etc
 * */
@Composable
private fun StockTrackerText(
  modifier: Modifier = Modifier,
  maxLines: Int = Int.MAX_VALUE,
  fontSize: FontSize = FontSize.Medium,
  fontFamily: FontFamily = FontFamily.Body,
  textAlign: TextAlign? = null,
  fontWeight: FontWeight? = null,
  text: String,
  color: Color = Color.Unspecified
) {

  val baseStyle = getFontStyle(fontSize = fontSize, fontFamily = fontFamily)
  val textStyle = baseStyle.copy(fontWeight = fontWeight).takeIf { fontWeight != null } ?: baseStyle
  Text(
    modifier = modifier,
    style = textStyle,
    text = text,
    color = color,
    maxLines = maxLines,
    overflow = TextOverflow.Ellipsis,
    textAlign = textAlign
  )
}

@Composable
private fun getFontStyle(fontSize: FontSize, fontFamily: FontFamily): TextStyle {
  return when (fontSize) {
    FontSize.Large -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineLarge
        FontFamily.Title -> MaterialTheme.typography.titleLarge
        FontFamily.Body -> MaterialTheme.typography.bodyLarge
        FontFamily.Label -> MaterialTheme.typography.labelLarge
      }
    }

    FontSize.Medium -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineMedium
        FontFamily.Title -> MaterialTheme.typography.titleMedium
        FontFamily.Body -> MaterialTheme.typography.bodyMedium
        FontFamily.Label -> MaterialTheme.typography.labelMedium
      }
    }

    FontSize.Small -> {
      when (fontFamily) {
        FontFamily.Headline -> MaterialTheme.typography.headlineSmall
        FontFamily.Title -> MaterialTheme.typography.titleSmall
        FontFamily.Body -> MaterialTheme.typography.bodySmall
        FontFamily.Label -> MaterialTheme.typography.labelSmall
      }
    }
  }
}

enum class FontFamily {
  Headline,
  Title,
  Body,
  Label,
}


enum class FontSize {
  Large,
  Medium,
  Small
}

@Composable
fun LargeHeading(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified) {
  StockTrackerText(
    modifier = modifier,
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Headline,
    text = text,
    color = color
  )
}

@Composable
fun MediumHeading(modifier: Modifier = Modifier, text: String, color: Color = Color.Unspecified) {
  StockTrackerText(
    modifier = modifier,
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Headline,
    text = text,
    color = color
  )
}

@Composable
fun SmallHeading(text: String, color: Color = Color.Unspecified) {
  StockTrackerText(
    fontSize = FontSize.Small,
    fontFamily = FontFamily.Headline,
    text = text,
    color = color
  )
}

@Composable
fun LargeTitle(
  modifier: Modifier = Modifier,
  text: String,
  fontFamily: FontFamily = FontFamily.Title,
  color: Color = Color.Unspecified
) {
  StockTrackerText(
    modifier = modifier,
    fontSize = FontSize.Large,
    fontFamily = fontFamily,
    text = text,
    color = color
  )
}

@Composable
fun MediumTitle(text: String, fontWeight: FontWeight? = null, color: Color = Color.Unspecified) {
  StockTrackerText(
    fontSize = FontSize.Medium,
    fontFamily = FontFamily.Title,
    fontWeight = fontWeight,
    text = text,
    color = color
  )
}

@Composable
fun SmallTitle(text: String, maxLines: Int = Int.MAX_VALUE, color: Color = Color.Unspecified) {
  StockTrackerText(
    fontSize = FontSize.Small,
    maxLines = maxLines,
    fontFamily = FontFamily.Title,
    text = text,
    color = color
  )
}

@Composable
fun LargeBody(text: String, color: Color = Color.Unspecified) {
  StockTrackerText(
    fontSize = FontSize.Large,
    fontFamily = FontFamily.Body,
    text = text,
    color = color
  )
}

@Composable
fun MediumBody(
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null
) {
  StockTrackerText(
    fontSize = FontSize.Medium,
    maxLines = maxLines,
    fontFamily = FontFamily.Body,
    textAlign = textAlign,
    text = text,
    color = color
  )
}

@Composable
fun SmallBody(
  text: String,
  maxLines: Int = Int.MAX_VALUE,
  fontWeight: FontWeight? = null,
  color: Color = Color.Unspecified,
  textAlign: TextAlign? = null
) {
  StockTrackerText(
    fontSize = FontSize.Small,
    fontWeight = fontWeight,
    fontFamily = FontFamily.Body,
    textAlign = textAlign,
    text = text,
    color = color,
    maxLines = maxLines
  )
}

