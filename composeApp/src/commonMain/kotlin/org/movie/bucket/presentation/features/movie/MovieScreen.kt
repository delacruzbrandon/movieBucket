package org.movie.bucket.presentation.features.movie

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun MovieScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.windowInsetsPadding(WindowInsets.statusBars)
    ) {
        MovieContent()
    }
}