package com.moneybase.stocktracker.common.base

interface Intention {
  val repeatAfter: Long
    get() = 0L
}
