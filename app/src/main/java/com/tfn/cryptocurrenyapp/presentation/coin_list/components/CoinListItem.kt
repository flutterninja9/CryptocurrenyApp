package com.tfn.cryptocurrenyapp.presentation.coin_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfn.cryptocurrenyapp.domain.model.Coin

@Composable
fun CoinListItem(
    coin: Coin,
    onTap: (Coin) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onTap(coin) }
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = coin.rank.toString() + ". "+ coin.name, overflow = TextOverflow.Ellipsis, style = MaterialTheme.typography.bodyLarge)
        Text(
            text = if(coin.isActive) "active" else "inactive",
            textAlign = TextAlign.End,
            style = MaterialTheme
                .typography
                .bodyMedium
                .copy(
                    color = if(coin.isActive) androidx.compose.ui.graphics.Color.Green else androidx.compose.ui.graphics.Color.Red
                ),
            fontStyle = FontStyle.Italic,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCoinListItem() {
    return Column {
        CoinListItem(
            coin = Coin(
                id = "coinId",
                name = "coinName",
                isActive = true,
                rank = 1,
                symbol = "MyCoin",
            ),
            onTap = {}
        )

        CoinListItem(
            coin = Coin(
                id = "coinId",
                name = "coinName",
                isActive = false,
                rank = 2,
                symbol = "MyCoin",
            ),
            onTap = {}
        )
    }
}