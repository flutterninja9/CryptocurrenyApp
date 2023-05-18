package com.tfn.cryptocurrenyapp.presentation.coin_detail.viewmodels

import com.tfn.cryptocurrenyapp.presentation.coin_list.viewmodels.CoinListState

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfn.cryptocurrenyapp.common.Constants
import com.tfn.cryptocurrenyapp.common.Resource
import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.use_case.get_coin.GetCoinDetailsUseCase
import com.tfn.cryptocurrenyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinDetailViewModel @Inject constructor(
    private val getCoinDetails: GetCoinDetailsUseCase,
    private val savedStateHandle: SavedStateHandle,
): ViewModel() {
    private val _state = mutableStateOf(CoinDetailState())
    val state: State<CoinDetailState>
        get() = _state

    init {
        savedStateHandle.get<String>(Constants.paramsCoinId)?.let {
                coinId -> getCoinList(coinId)
        }
    }

    private fun getCoinList(coinId: String) {
        getCoinDetails(coinId).onEach { result ->
            when(result) {
                is Resource.Failed -> {
                    _state.value = CoinDetailState(error = result.message ?: "Something went wrong!")
                }
                is Resource.Loaded -> {
                    _state.value = CoinDetailState(data = result.data!!)
                }
                is Resource.Loading -> {
                    _state.value = CoinDetailState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}