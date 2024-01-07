package com.example.tbo_probeaufgabe.data.local.test

import com.example.tbo_probeaufgabe.data.remote.model.CoinApiModel
import com.example.tbo_probeaufgabe.domain.model.Coin
import com.example.tbo_probeaufgabe.domain.model.CoinHistoryPrice

/**
 * Created by nurseiit.tursunkulov on 07.01.24.
 */


/**
 * Created by nurseiit.tursunkulov on 07.01.2024
 * CoinListWithHistory
 */
val coinsWithHistory = listOf(
    Coin(
        id = "bitcoin",
        symbol = "btc",
        name = "Bitcoin",
        image = "https://assets.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
        currentPrice = 40469.0,
        priceChange24h = 1446.19,
        priceChangePercentage24h = 3.70602,
        lastUpdated = "2024-01-04T19:15:39.288Z",
        historyPrice = CoinHistoryPrice(
            id = "bitcoin",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "ethereum",
        symbol = "eth",
        name = "Ethereum",
        image = "https://assets.coingecko.com/coins/images/279/large/ethereum.png?1696501628",
        currentPrice = 2090.03,
        priceChange24h = 61.56,
        priceChangePercentage24h = 3.03463,
        lastUpdated = "2024-01-04T19:15:14.940Z",
        historyPrice = CoinHistoryPrice(
            id = "ethereum",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "tether",
        symbol = "usdt",
        name = "Tether",
        image = "https://assets.coingecko.com/coins/images/325/large/Tether.png?1696501661",
        currentPrice = 0.916215,
        priceChange24h = -0.002104503274332537,
        priceChangePercentage24h = -0.22917,
        lastUpdated = "2024-01-04T19:15:22.662Z",
        historyPrice = CoinHistoryPrice(
            id = "tether",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "binancecoin",
        symbol = "bnb",
        name = "BNB",
        image = "https://assets.coingecko.com/coins/images/825/large/bnb-icon2_2x.png?1696501970",
        currentPrice = 293.67,
        priceChange24h = 5.89,
        priceChangePercentage24h = 2.04804,
        lastUpdated = "2024-01-04T19:15:16.717Z",
        historyPrice = CoinHistoryPrice(
            id = "binancecoin",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "solana",
        symbol = "sol",
        name = "Solana",
        image = "https://assets.coingecko.com/coins/images/4128/large/solana.png?1696504756",
        currentPrice = 95.33,
        priceChange24h = 5.82,
        priceChangePercentage24h = 6.50132,
        lastUpdated = "2024-01-04T19:15:45.094Z",
        historyPrice = CoinHistoryPrice(
            id = "solana",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "ripple",
        symbol = "xrp",
        name = "XRP",
        image = "https://assets.coingecko.com/coins/images/44/large/xrp-symbol-white-128.png?1696501442",
        currentPrice = 0.537989,
        priceChange24h = 0.01129716,
        priceChangePercentage24h = 2.14493,
        lastUpdated = "2024-01-04T19:15:31.572Z",
        historyPrice = CoinHistoryPrice(
            id = "ripple",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "usd-coin",
        symbol = "usdc",
        name = "USDC",
        image = "https://assets.coingecko.com/coins/images/6319/large/usdc.png?1696506694",
        currentPrice = 0.914776,
        priceChange24h = -0.003230640333491186,
        priceChangePercentage24h = -0.35192,
        lastUpdated = "2024-01-04T19:15:40.151Z",
        historyPrice = CoinHistoryPrice(
            id = "usd-coin",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "staked-ether",
        symbol = "steth",
        name = "Lido Staked Ether",
        image = "https://assets.coingecko.com/coins/images/13442/large/steth_logo.png?1696513206",
        currentPrice = 2083.82,
        priceChange24h = 59.13,
        priceChangePercentage24h = 2.9205,
        lastUpdated = "2024-01-04T19:14:57.260Z",
        historyPrice = CoinHistoryPrice(
            id = "staked-ether",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "cardano",
        symbol = "ada",
        name = "Cardano",
        image = "https://assets.coingecko.com/coins/images/975/large/cardano.png?1696502090",
        currentPrice = 0.524552,
        priceChange24h = 0.01529395,
        priceChangePercentage24h = 3.00318,
        lastUpdated = "2024-01-04T19:15:34.461Z",
        historyPrice = CoinHistoryPrice(
            id = "cardano",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    ),
    Coin(
        id = "avalanche-2",
        symbol = "avax",
        name = "Avalanche",
        image = "https://assets.coingecko.com/coins/images/12559/large/Avalanche_Circle_RedWhite_Trans.png?1696512369",
        currentPrice = 35.75,
        priceChange24h = 0.01529395,
        priceChangePercentage24h = 3.00318,
        lastUpdated = "2024-01-04T19:15:34.461Z",
        historyPrice = CoinHistoryPrice(
            id = "avalanche-2",
            prices = listOf(
                listOf("1703116800000", "39864.87762044923"),
                listOf("1703203200000", "39838.679877640185")
            )
        )
    )
    // Repeat the structure for other coins...
)

// You can continue adding more instances based on the same structure.
