package com.example.tbo_probeaufgabe.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp
import com.example.tbo_probeaufgabe.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CoinDetailScreen(
    viewModel: MainViewModel = koinViewModel()
) {
    val state = viewModel.selectedCoin.collectAsState()
    Text(text = "this future was not fully implemented yet")//todo fix problem with the state
    LazyColumn(
        modifier = Modifier,
        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp)
    ) {
        val prices = state.value.prices
            items(prices as List<List<String>>) { price ->
                // Display each price item here
                Text("Price: ${price[1]}   ${price[2]}") // Assuming the price data is at index 1
                Divider(color = Color.Blue)
            }

    }
}