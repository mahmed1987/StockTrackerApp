package com.moneybase.stocktracker.common.base

import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure

abstract class UiState()

inline fun <reified T : UiState> T.toRight(): Either<Failure, T> {
  return Either.Right(this)
}

