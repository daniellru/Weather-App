package com.example.weatherapp.api

sealed class NetWorkResponse <out T>{

    data class Success<out T>(val data: T): NetWorkResponse<T>()
    data class Error(val message: String): NetWorkResponse<Nothing>()
    data object Loading : NetWorkResponse<Nothing>()
}