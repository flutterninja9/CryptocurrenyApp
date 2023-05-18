package com.tfn.cryptocurrenyapp.presentation.coin_list.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.tfn.cryptocurrenyapp.domain.model.Coin
import com.tfn.cryptocurrenyapp.presentation.coin_list.viewmodels.CoinListViewModel

@Composable
fun CoinListScreen(
    onCoinTap: (Coin) -> Unit,
    viewModel: CoinListViewModel,
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(Modifier.fillMaxSize()) {
            items(state.data) {coin ->
                CoinListItem(
                    coin = coin,
                    onTap = {
                            onCoinTap(it)
                    },
                )
            }
        }
    }

    if(state.error.isNotBlank()) {
        Text(text = state.error, modifier = Modifier.fillMaxWidth().padding(20.dp))
    }

    if(state.isLoading) {
        CircularProgressIndicator()
    }
}