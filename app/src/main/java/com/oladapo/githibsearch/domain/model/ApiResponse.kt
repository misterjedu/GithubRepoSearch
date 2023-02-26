package com.oladapo.githibsearch.domain.model

//Sealed class to wrap api response with the data state.
sealed class ApiResponse<T> {
    class Loading<T> : ApiResponse<T>()
    data class Success<T>(val response: T) : ApiResponse<T>()
    data class Error<T>(val errorMessage: String?) : ApiResponse<T>()
}