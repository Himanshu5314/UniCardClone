package com.example.testapp.network

import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource {
    lateinit var retrofitService: RetrofitService

    fun init() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://catfact.ninja/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(
                OkHttpClient.Builder()
                    .addInterceptor { chain ->
                        val request: Request = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer your_token_here")
                            .build()
                        chain.proceed(request)
                    }
                    .build()
            )
            .build()
        retrofitService = retrofit.create(RetrofitService::class.java)
    }
}