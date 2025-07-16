package org.movie.bucket

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.movie.bucket.data.network.CocktailClient
import org.movie.bucket.data.network.KtorMovieClient
import org.movie.bucket.domain.builder.createHttpClient

fun MainViewController() = ComposeUIViewController {
    App(
        cocktailClient = remember {
            CocktailClient(
                httpClient = createHttpClient(
                    engine = Darwin.create()
                )
            )
        },
        ktorMovieClient = remember {
            KtorMovieClient(
                httpClient = createHttpClient(
                    engine = Darwin.create()
                )
            )
        },
    )
}