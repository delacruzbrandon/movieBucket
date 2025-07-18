package org.movie.bucket

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import org.movie.bucket.presentation.features.movie.MovieScreen

@Composable
fun CommonApp() {
    MaterialTheme() {
        MovieScreen(

        )
    }
}