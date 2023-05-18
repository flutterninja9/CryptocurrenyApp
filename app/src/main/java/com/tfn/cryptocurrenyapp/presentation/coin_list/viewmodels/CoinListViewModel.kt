package com.tfn.cryptocurrenyapp.presentation.coin_list.viewmodels

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfn.cryptocurrenyapp.common.Resource
import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.domain.use_case.get_coins.GetCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CoinListViewModel @Inject constructor(
    private val getCoinsUseCase: GetCoinsUseCase
): ViewModel() {
    private val _state = mutableStateOf(CoinListState())
    val state: State<CoinListState>
        get() = _state

    init {
        getCoinList()
    }

    private fun getCoinList() {
        getCoinsUseCase().onEach { result ->
            when(result) {
                is Resource.Failed -> {
                    _state.value = CoinListState(error = result.message ?: "Something went wrong!")
                }
                is Resource.Loaded -> {
                    _state.value = CoinListState(data = result.data ?: emptyList())
                }
                is Resource.Loading -> {
                    _state.value = CoinListState(isLoading = true)
                }
            }
        }.launchIn(viewModelScope)
    }
}