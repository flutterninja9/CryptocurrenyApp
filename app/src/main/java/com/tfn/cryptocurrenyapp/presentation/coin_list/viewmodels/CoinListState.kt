package com.tfn.cryptocurrenyapp.presentation.coin_list.viewmodels

import com.tfn.cryptocurrenyapp.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = true,
    val data: List<Coin> = emptyList(),
    val error: String = ""
)