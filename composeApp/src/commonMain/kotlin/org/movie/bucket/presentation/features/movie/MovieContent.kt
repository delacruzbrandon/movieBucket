package org.movie.bucket.presentation.features.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.movie.bucket.presentation.features.home.HomeViewModel
import org.movie.bucket.presentation.features.movie.composables.LikeButton
import org.movie.bucket.presentation.features.movie.composables.SkipButton
import org.movie.bucket.presentation.features.movie.composables.SuperLikeButton
import util.NetworkError

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    movieViewModel: HomeViewModel = koinViewModel()
) {
    val movieListState by movieViewModel.movieList.collectAsStateWithLifecycle()
    val randomMovieState by movieViewModel.randomMovie.collectAsStateWithLifecycle()

    var isLoading by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit)  {
        movieViewModel.getPopularMovies()
    }

    LaunchedEffect(movieListState)  {
        movieViewModel.getRandomMovie()
    }

    LaunchedEffect(movieListState)  {
        movieViewModel.getErrorMessage()
    }
    randomMovieState?.let { details ->
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Box(
                modifier = modifier
                    .height(IntrinsicSize.Min)
            ) {
                AsyncImage(
                    modifier = modifier
                        .fillMaxSize(),
                    model = "https://image.tmdb.org/t/p/w200" + details.thumbnail,
                    contentDescription = details.thumbnail
                )

                Text(
                    details.rating.toString(),
                    modifier = modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.TopEnd)
                )
            }
            Column(
                modifier = modifier
                    .weight(4f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    details.title,
                    style = MaterialTheme.typography.headlineSmall
                )
                Text(
                    details.releaseDate,
                    style = MaterialTheme.typography.labelLarge
                )

                details.description?.let {
                    Text(
                        it,
                        modifier,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis
                        )
                }
                Text(details.language)
            }

//        errorMessage?.let {
//            Text(
//                text = it.name,
//                color = Color.Red
//            )
//        }

            SkipOrLikeButtons(
                modifier = modifier
                    .weight(1f),
                onSkip = {
                    scope.launch {
                        isLoading = true
                        movieViewModel.getRandomMovie()
                        isLoading = false
                    }
                },
                onLike = {
                    scope.launch {
                        isLoading = true
                        movieViewModel.getRandomMovie()
                        isLoading = false
                    }
                },
                onSuperLike = {
                    scope.launch {
                        isLoading = true
                        movieViewModel.getRandomMovie()
                        isLoading = false
                    }
                }
            )
        }
    }
}

@Composable
@Preview
fun SkipOrLikeButtons(
    modifier: Modifier = Modifier,
    onSkip: () -> Unit,
    onSuperLike: () -> Unit,
    onLike: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 40.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        SkipButton(
            modifier = modifier
                .size(78.dp),
            onSkip = onSkip
        )
        SuperLikeButton(
            modifier = modifier
                .size(98.dp)
                .fillMaxHeight(),
            onSuperLike = onSuperLike
        )
        LikeButton(
            modifier = modifier
                .size(78.dp),
            onLike = onLike
        )
    }
}