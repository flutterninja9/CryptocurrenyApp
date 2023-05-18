package com.tfn.cryptocurrenyapp.domain.repository

import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.model.CoinDetail

interface CoinRepository {
    suspend fun getCoins() : List<Coin>

    suspend fun getCoin(id: String): CoinDetail
}