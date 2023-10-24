package com.moneybase.stocktracker.common.base

import androidx.lifecycle.SavedStateHandle
import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import kotlinx.coroutines.CoroutineDispatcher

class TestViewModel(ioDispatcher: CoroutineDispatcher, savedStateHandle: SavedStateHandle) :
  BaseViewModel<FakeIntention, FakeUiState>(ioDispatcher) {
  override suspend fun reduce(
    uiState: FakeUiState,
    intention: FakeIntention
  ): Either<Failure, FakeUiState> {
    return when (intention) {
      FakeIntention.MutateStateByTen -> uiState.copy(number = 10).toRight()
      FakeIntention.RepeatingIntention -> uiState.copy(number = uiState.number + 1).toRight()
    }
  }

  override fun initializeState(): FakeUiState = FakeUiState(1)

}