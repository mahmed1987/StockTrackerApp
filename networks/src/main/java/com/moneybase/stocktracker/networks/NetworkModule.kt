package com.moneybase.stocktracker.networks

import com.moneybase.stocktracker.dtos.system.RequestHeaders
import com.moneybase.stocktracker.networks.stocks.StocksWebServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


  @Provides
  @Singleton
  fun provideRequestInterceptor(): RequestInterceptor {
    return RequestInterceptor(RequestHeaders)
  }

  @Provides
  @Singleton
  fun provideRetrofit(requestInterceptor: RequestInterceptor): Retrofit {
    val httpClient = OkHttpClient.Builder()
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    val logger =
      httpLoggingInterceptor.apply {
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
      }
    httpClient.addInterceptor(requestInterceptor)
    httpClient.addInterceptor(logger)
    val retroBuilder =
      Retrofit.Builder()
        .baseUrl(BuildConfig.API_ENDPOINT)
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(ScalarsConverterFactory.create())
    retroBuilder.client(
      httpClient.readTimeout(60, TimeUnit.SECONDS)
        .connectTimeout(60, TimeUnit.SECONDS).build()
    )
    return retroBuilder.build()
  }

  @Provides
  @Singleton
  fun provideStockWebservices(retrofit: Retrofit): StocksWebServices {
    return retrofit.create(StocksWebServices::class.java)
  }

}