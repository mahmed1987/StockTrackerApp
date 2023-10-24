package com.moneybase.stocktracker.networks

import com.moneybase.stocktracker.dtos.system.Either
import com.moneybase.stocktracker.dtos.system.Failure
import retrofit2.Response

/**
 * A utility class that converts retrofit responses to Either*/
suspend fun <ResponseType> networkCall(call: suspend () -> Response<ResponseType>): Either<Failure, ResponseType> {
  try {
    val response = call()
    return if (!response.isSuccessful) {
      when (response.code()) {
        401 -> Either.Left(Failure.AuthError)
        403 -> Either.Left(Failure.Forbidden)
        400 -> Either.Left(Failure.BadRequest)
        404 -> Either.Left(Failure.NotFound)
        415 -> Either.Left(Failure.UnSupportedMediaType)
        500 -> Either.Left(Failure.InternalServerError)
        else -> Either.Left(Failure.ServerError)
      }
    } else {
      Either.Right(response.body()!!)
    }
  } catch (exception: Exception) {
    return Either.Left(Failure.NetworkConnection)
  }


}