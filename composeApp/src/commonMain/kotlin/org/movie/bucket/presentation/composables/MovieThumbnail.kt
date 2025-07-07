package org.movie.bucket.presentation.composables

import androidx.compose.animation.core.copy
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Star
import moviebucket.composeapp.generated.resources.Res
import moviebucket.composeapp.generated.resources.icon_star
import moviebucket.composeapp.generated.resources.image_movie_thumbnail
import org.jetbrains.compose.resources.stringResource

@Composable
fun MovieThumbnail(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        AsyncImage(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .fillMaxHeight()
                .wrapContentWidth(),
            model = "https://image.tmdb.org/t/p/w200/wqfu3bPLJaEWJVk3QOm0rKhxf1A.jpg",
            contentDescription = stringResource(Res.string.image_movie_thumbnail)
        )

        // Second composable (top layer)
        Box(
            modifier = Modifier
                .wrapContentSize()
                .padding(8.dp)
                .background(Color(0xF252836).copy(alpha = 0.32f), shape = RoundedCornerShape(8.dp)) // Use alpha for transparency
                .align(Alignment.TopStart) // Center this Box as well
        ) {
            Row(
                modifier = Modifier
                    .padding(vertical = 4.dp, horizontal = 8.dp)
            ) {
                Icon(
                    imageVector = EvaIcons.Fill.Star,
                    tint = Color(0xFFFF8700),
                    contentDescription = stringResource(Res.string.icon_star)
                )
                Spacer(modifier = Modifier.width(0.5.dp))
                Text(
                    text = "4.5",
                    color = Color(0xFFFF8700),
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}