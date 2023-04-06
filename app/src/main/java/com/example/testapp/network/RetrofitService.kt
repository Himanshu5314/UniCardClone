package com.example.testapp.network

import com.example.testapp.model.response.CatFact
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitService {
    @GET("{path}")
    fun getCatFact(@Path("path") path: String?): Call<CatFact>
}