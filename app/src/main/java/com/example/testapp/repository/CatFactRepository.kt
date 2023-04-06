package com.example.testapp.repository

import com.example.testapp.model.response.CatFact
import com.example.testapp.network.RetrofitService
import retrofit2.Call
import javax.inject.Inject

class CatFactRepository @Inject constructor(private val retrofitService: RetrofitService) {
    suspend fun getCatFacts(): Call<CatFact> {
        return retrofitService.getCatFact("fact")
    }
}