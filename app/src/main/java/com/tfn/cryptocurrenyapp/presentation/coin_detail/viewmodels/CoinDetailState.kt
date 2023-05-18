package com.tfn.cryptocurrenyapp.presentation.coin_detail.viewmodels

import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = true,
    val data: CoinDetail? = null,
    val error: String = ""
)