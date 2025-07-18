package org.movie.bucket.presentation.features.movie.composables

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Heart

@Composable
fun LikeButton(
    modifier: Modifier = Modifier,
    onLike: () -> Unit,
) {
    Button(
        modifier = modifier,
        onClick = onLike,
        shape = CircleShape
    ) {
        Icon(
            modifier = modifier
                .size(30.dp),
            imageVector = EvaIcons.Fill.Heart,
            contentDescription = "Like",
            tint = Color.White
        )
    }
}