package org.movie.bucket

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            CommonApp()
//                cocktailClient = remember {
//                    CocktailClient(
//                        httpClient = createHttpClient(
//                            engine = OkHttp.create()
//                        )
//                    )
//                },
//                ktorMovieClient = remember {
//                    KtorMovieClient(
//                        httpClient = createHttpClient(
//                            engine = OkHttp.create()
//                        )
//                    )
//                },
//            )
        }
    }
}