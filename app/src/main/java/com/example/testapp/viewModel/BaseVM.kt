package com.example.testapp.viewModel

import androidx.lifecycle.ViewModel
import com.example.testapp.network.model.RepoResult
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

open class BaseVM: ViewModel() {
    open fun <T> apiResponseCallback(response: Call<T>, responseListener: ResponseListener<T>) {
        response.enqueue(object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {
                if(response.isSuccessful) {
                    CoroutineScope(Dispatchers.IO).launch {
                        responseListener.onResponseReceived(RepoResult(data = response.body(), statusCode = response.code()))
                    }
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        responseListener.onResponseReceived(RepoResult(errorMessage = response.message(), statusCode = response.code()))
                    }
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                CoroutineScope(Dispatchers.IO).launch {
                    responseListener.onResponseReceived(RepoResult(failureMessage = t.message))
                }
            }
        })
    }

    interface ResponseListener <T> {
        suspend fun onResponseReceived(response: RepoResult<T>?)
    }
}