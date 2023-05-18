package com.tfn.cryptocurrenyapp.data.repository

import com.tfn.cryptocurrenyapp.data.data_source.CoinPaprikaApi
import com.tfn.cryptocurrenyapp.data.dto.toCoin
import com.tfn.cryptocurrenyapp.data.dto.toCoinDetail
import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.model.CoinDetail
import com.tfn.cryptocurrenyapp.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val api: CoinPaprikaApi,
) : CoinRepository {
    override suspend fun getCoins(): List<Coin> {
        return api.getCoins().map { it.toCoin() }
    }

    override suspend fun getCoin(id: String): CoinDetail {
        return api.getCoinById(id).toCoinDetail()
    }

}