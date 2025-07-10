package org.movie.bucket

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import io.ktor.client.engine.darwin.Darwin
import org.movie.bucket.data.InsultCensorClient
import org.movie.bucket.data.createHttpClient

fun MainViewController() = ComposeUIViewController { App(
    client = remember {
        InsultCensorClient(
            httpClient = createHttpClient(
                engine = Darwin.create()
            )
        )
    }
) }