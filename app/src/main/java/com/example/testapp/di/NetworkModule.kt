package com.example.testapp.di

import com.example.testapp.network.RemoteDataSource
import com.example.testapp.network.RetrofitService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Singleton
    @Provides
    fun provideApiService(): RetrofitService = RemoteDataSource.retrofitService
}