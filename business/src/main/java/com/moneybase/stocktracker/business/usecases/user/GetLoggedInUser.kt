package com.moneybase.stocktracker.business.usecases.user

import com.moneybase.stocktracker.dtos.R
import com.moneybase.stocktracker.dtos.user.ViewUser

/**
 * A non-functional use case to get the logged in user . In reality we would expose a Flow that observes
 * the user table in Room
 *
 * Test case can check if two successive calls produces different usernames
 * */
object GetLoggedInUser {
  operator fun invoke() = ViewUser(id = "123", name = generateRandomUsername())

  private val words = listOf("apple", "banana", "cherry", "date", "fig", "grape", "kiwi")
  private fun getRandomWord(): String {
    return words.random()
  }

  fun generateRandomUsername(): String {
    return "${getRandomWord().capitalize()}${getRandomWord().capitalize()}"
  }
}