package org.movie.bucket

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.movie.bucket.core.data.network.CocktailClient
import org.movie.bucket.core.data.network.InsultCensorClient
import org.movie.bucket.core.data.network.createHttpClient

fun MainViewController() = ComposeUIViewController { App(
    insultClient = remember {
        InsultCensorClient(
            httpClient = createHttpClient(
                engine = Darwin.create()
            )
        )
    },
    cocktailClient = remember {
        CocktailClient(
            httpClient = createHttpClient(
                engine = Darwin.create()
            )
        )
    }
) }