package com.example.tbo_probeaufgabe.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.tbo_probeaufgabe.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinListScreen(
    navController: NavController,
    viewModel: MainViewModel = koinViewModel()
) {
    val coins by viewModel.state.collectAsState()
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        items(coins.coins) { coin ->
            Text(text = coin.name)
            CoinListItem(coin = coin, onItemClick = {
                navController.navigate(Screen.CoinDetailScreen.route + "/${coin.id}")
                viewModel.setSelCoin(coin)
            })
            Divider(color = Color.Blue)
        }
    }
}