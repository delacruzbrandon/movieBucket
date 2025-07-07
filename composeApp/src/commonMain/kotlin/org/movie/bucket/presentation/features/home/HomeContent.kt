package org.movie.bucket.presentation.features.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Calendar
import compose.icons.evaicons.fill.Clock
import compose.icons.evaicons.fill.Film
import moviebucket.composeapp.generated.resources.Res
import moviebucket.composeapp.generated.resources.compose_multiplatform
import org.jetbrains.compose.resources.painterResource
import org.movie.bucket.Greeting
import org.movie.bucket.presentation.composables.MovieThumbnail
import org.movie.bucket.presentation.composables.RowIconText

@Composable
fun HomeContent() {
    var showContent by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .safeContentPadding()
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        MovieList()
        Button(onClick = { showContent = !showContent }) {
            Text("Click me!")
        }
        AnimatedVisibility(showContent) {
            val greeting = remember { Greeting().greet() }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painterResource(Res.drawable.compose_multiplatform), null)
                Text("Compose: $greeting")
            }
        }
    }
}

@Composable
private fun MovieList(
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(6) {
            MovieItem()
        }
    }
}

@Composable
private fun MovieItem(
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {
        MovieThumbnail(
            modifier = modifier
                .fillMaxHeight()
        )
        Column(
            modifier = modifier
                .fillMaxHeight()
                .padding(vertical = 5.dp, horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Box(
                modifier = modifier
                    .background(
                        color = Color(0xFFFF8700),
                        shape = RoundedCornerShape(6.dp)
                    ),
            ) {
                Text("English",
                    modifier = modifier
                        .padding(vertical = 2.dp, horizontal = 8.dp),
                    fontSize = 10.sp,
                    color = Color.White
                )
            }
            Text(text = "The Old Guard 2",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp
            )
            RowIconText(
                icon = EvaIcons.Fill.Calendar,
                text = "2021"
            )
            RowIconText(
                icon = EvaIcons.Fill.Clock,
                text = "148 Minutes"
            )
            RowIconText(
                icon = EvaIcons.Fill.Film,
                text = "Action"
            )
        }
    }
}