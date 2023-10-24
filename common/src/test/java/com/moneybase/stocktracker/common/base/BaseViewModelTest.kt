package com.moneybase.stocktracker.common.base

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain

import org.junit.Before
import org.junit.Test

class BaseViewModelTest {
  private lateinit var testDispatcher: TestDispatcher
  private lateinit var viewModel: TestViewModel

  @Before
  fun setup() {
    testDispatcher = StandardTestDispatcher()
    val savedState = SavedStateHandle(mapOf("someIdArg" to 1))
    viewModel = TestViewModel(testDispatcher, savedState)
  }


  @Test
  fun `When intention is executed , state is changed`() {
    viewModel.executeIntention(FakeIntention.MutateStateByTen)
    testDispatcher.scheduler.advanceUntilIdle()
    val uiState = viewModel.uiState.value
    assertThat(uiState.number).isEqualTo(10)
  }
//


  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `Execute repeating intention emits multiple times`() = runBlockingTest {
    val repeatCount = 10
    val totalDuration = FakeIntention.RepeatingIntention.repeatAfter * repeatCount

    viewModel.uiState.test {
      viewModel.executeIntention(FakeIntention.RepeatingIntention)
      testDispatcher.scheduler.advanceTimeBy(totalDuration)

      // Assuming that RepeatingIntention modifies the number by +1 each time:
      for (i in 1..repeatCount) {
        assertThat(awaitItem().number).isEqualTo(i)
      }
      cancelAndConsumeRemainingEvents()
    }
  }

  @Test
  fun `Executing new intention cancels previous job`() = runBlockingTest {
    // Start by capturing the initial state
    val startState = viewModel.uiState.value

    // Invoke the repeating intention
    viewModel.executeIntention(FakeIntention.RepeatingIntention)

    // Here we move forward just enough to have the second emission in progress.
    testDispatcher.scheduler.advanceTimeBy(FakeIntention.RepeatingIntention.repeatAfter - 500)

    // This is what we have after the first invocation of the RepeatingIntention
    val stateAfterOneRepetition = viewModel.uiState.value

    // Assert that the RepeatingIntention has emitted once
    assertThat(stateAfterOneRepetition.number).isEqualTo(startState.number + 1)

    viewModel.executeIntention(FakeIntention.MutateStateByTen)   // This cancels the above intention.

    // Move forward beyond the repeating interval
    testDispatcher.scheduler.advanceTimeBy(FakeIntention.RepeatingIntention.repeatAfter + 500)

    // If RepeatingIntention is still in progress , we wont have 10 when the MutateStateByTen intention is executed
    val finalState = viewModel.uiState.value

    assertThat(finalState.number).isEqualTo(10)

  }

  @OptIn(ExperimentalCoroutinesApi::class)
  @Test
  fun `Check loading state when intention is executed`() = runBlockingTest {
    viewModel.executeIntention(FakeIntention.MutateStateByTen)
    viewModel.loading.test {
      testDispatcher.scheduler.advanceUntilIdle()
      assertThat(awaitItem()).isTrue()  // Check loading state is true at the beginning
      assertThat(awaitItem()).isFalse() // Check loading state is false at the end
      cancelAndConsumeRemainingEvents()
    }
  }


}