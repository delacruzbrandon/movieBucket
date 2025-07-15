package org.movie.bucket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import io.ktor.client.engine.okhttp.OkHttp
import org.movie.bucket.data.network.CocktailClient
import org.movie.bucket.data.network.MovieClient
import org.movie.bucket.domain.builder.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                cocktailClient = remember {
                    CocktailClient(
                        httpClient = createHttpClient(
                            engine = OkHttp.create()
                        )
                    )
                },
                movieClient = remember {
                    MovieClient(
                        httpClient = createHttpClient(
                            engine = OkHttp.create()
                        )
                    )
                },
            )
        }
    }
}