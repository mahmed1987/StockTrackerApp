package com.moneybase.stocktracker.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * This is the root ViewModel that every other view model should inherit .
 * It is a mechanism where which our logics of pulling data from the viewmodel to the UI can be greatly organised
 * */
abstract class BaseViewModel<I : Intention, U : UiState>(private val dispatcher: CoroutineDispatcher = Dispatchers.IO) :
  ViewModel() {
  // Keeping track of the current intention job
  private var currentIntentionJob: Job? = null

  //the event shared flow . This is NOT a state flow because we would want to emit the same events (intentions) more than once
  private val event = MutableSharedFlow<I>(replay = 1)

  //the main uiState of the screen , that is plucked out of the savedstatehandle
  private val _uiState: MutableStateFlow<U> by lazy { MutableStateFlow(initializeState()) }
  val uiState: StateFlow<U> = _uiState.asStateFlow()
  private val _failure: MutableSharedFlow<Failure> = MutableSharedFlow(replay = 0)
  val failure: SharedFlow<Failure> = _failure.asSharedFlow()

  private val _loading: MutableSharedFlow<Boolean> = MutableSharedFlow(replay = 0)
  val loading: SharedFlow<Boolean> = _loading.asSharedFlow()

  init {
    viewModelScope.launch(dispatcher) {
      event.collect {
        _loading.emit(true)
        withContext(dispatcher) {
          reduce(uiState.value, it).eitherAsync({
            _failure.emit(it)
            _loading.emit(false)
          }, { uiState ->
            _uiState.value = uiState
            _loading.emit(false)
          })
        }
      }
    }
  }

  abstract suspend fun reduce(uiState: U, intention: I): Either<Failure, U>

  fun executeIntention(intention: I) {
    // Cancel the previous job if it's still active
    currentIntentionJob?.cancel()

    currentIntentionJob = viewModelScope.launch(dispatcher) {
      do {
        event.emit(intention)
        val delayTime = intention.repeatAfter
        if (delayTime > 0L) {
          delay(delayTime)  // Wait for the specified amount of time before the next emission
        }
      } while (delayTime > 0L && isActive)
    }
  }

  abstract fun initializeState(): U


}