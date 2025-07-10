package org.movie.bucket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import io.ktor.client.engine.okhttp.OkHttp
import org.movie.bucket.data.InsultCensorClient
import org.movie.bucket.data.createHttpClient

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            App(
                client = remember {
                    InsultCensorClient(
                        httpClient = createHttpClient(
                            engine = OkHttp.create()
                        )
                    )
                }
            )
        }
    }
}