package com.example.testapp.viewModel

import com.example.testapp.model.response.CatFact
import com.example.testapp.network.model.RepoResult
import com.example.testapp.repository.CatFactRepository
import com.example.testapp.state.CatState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(private val repository: CatFactRepository) : BaseVM() {
    private val mutableCatStateFlow = MutableStateFlow<CatState>(CatState.Idle)
    val catStateFlow = mutableCatStateFlow.asStateFlow()
    fun getCatFact() {
        CoroutineScope(Dispatchers.IO).launch {
            mutableCatStateFlow.emit(CatState.Loading)
            val response = repository.getCatFacts()
            apiResponseCallback(response, responseListener = object : ResponseListener<CatFact> {
                override suspend fun onResponseReceived(response: RepoResult<CatFact>?) {
                    if (response?.failureMessage != null) {
                        mutableCatStateFlow.emit(CatState.Failure(response.failureMessage))
                    } else if (response?.errorMessage != null) {
                        mutableCatStateFlow.emit(CatState.Failure(response.errorMessage))
                    } else if (response?.data != null) {
                        mutableCatStateFlow.emit(CatState.Success(response.data))
                    }
                }
            })
        }
    }
}