package com.moneybase.stocktracker.dtos.system

/**
 * Contains the request headers for the api call.
 * */
object RequestHeaders {
  val headers: HashMap<String, String> = hashMapOf()
  init {
    setLanguageType("en")
    setToken("a9d141142f504e549f8fb8db644e593a")
  }

  fun setToken(token: String) {
    headers["Authorization"] = "apikey $token"
  }

  fun setLanguageType(language: String) {
    headers["Accept-Language"] = language
  }

  fun removeToken() {
    headers.remove("Authorization")
  }

}