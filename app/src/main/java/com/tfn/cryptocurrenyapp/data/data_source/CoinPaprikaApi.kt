package com.tfn.cryptocurrenyapp.data.data_source

import com.tfn.cryptocurrenyapp.data.dto.CoinDetailDto
import com.tfn.cryptocurrenyapp.data.dto.CoinDto
import retrofit2.http.GET
import retrofit2.http.Path

interface CoinPaprikaApi {
    @GET("/v1/coins")
    suspend fun getCoins(): List<CoinDto>

    @GET("/v1/coins/{coinId}")
    suspend fun getCoinById(@Path("coinId") coinId: String): CoinDetailDto
}