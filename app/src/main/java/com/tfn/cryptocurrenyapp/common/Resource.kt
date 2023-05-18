package com.tfn.cryptocurrenyapp.common

sealed class Resource<T>(val data: T? = null, val message: String? = null) {
    class Loaded<T>(data: T) : Resource<T>(data = data)
    class Failed<T>(message: String, data: T? = null) : Resource<T>(message = message, data = data)
    class Loading<T>() : Resource<T>()
}