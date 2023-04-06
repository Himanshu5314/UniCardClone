package com.example.testapp.network.model

data class RepoResult<T>(
    var data: T? = null,
    var errorMessage: String? = null,
    var failureMessage: String? = null,
    val statusCode: Int? = null
)
