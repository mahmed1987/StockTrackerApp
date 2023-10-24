package com.moneybase.stocktracker.common.base

sealed class FakeIntention : Intention {
  object MutateStateByTen : FakeIntention()
  object RepeatingIntention : FakeIntention(){
    override val repeatAfter: Long
      get() = 2000L
  }
}