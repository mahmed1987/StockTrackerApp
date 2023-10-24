package com.moneybase.stocktracker.repositories.di

import com.moneybase.stocktracker.repositories.stocks.StocksDataSource
import com.moneybase.stocktracker.repositories.stocks.StocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
  @Binds
  abstract fun bindStocksRepository(impl: StocksRepository): StocksDataSource
}