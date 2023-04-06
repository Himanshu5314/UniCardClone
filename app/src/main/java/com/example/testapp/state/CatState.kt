package com.example.testapp.state

import com.example.testapp.model.response.CatFact

sealed class CatState {
    object Idle: CatState()
    object Loading : CatState()
    class Success(val data: CatFact?): CatState()
    class Failure(val failureMessage: String?): CatState()
}
