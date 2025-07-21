package org.movie.bucket.presentation.features.movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import compose.icons.EvaIcons
import compose.icons.evaicons.Fill
import compose.icons.evaicons.fill.Close
import compose.icons.evaicons.fill.Flash
import compose.icons.evaicons.fill.Heart
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.movie.bucket.presentation.features.home.HomeViewModel
import util.NetworkError

@Composable
fun MovieContent(
    modifier: Modifier = Modifier,
    movieViewModel: HomeViewModel = koinViewModel()
) {
    val movieListState by movieViewModel.movieList.collectAsStateWithLifecycle()
    val randomMovieState by movieViewModel.randomMovie.collectAsStateWithLifecycle()
    val randomMovieGenresState by movieViewModel.genreList.collectAsStateWithLifecycle()

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

    LaunchedEffect(randomMovieState)  {
        movieViewModel.getRandomMovieGenres()
    }

    LaunchedEffect(movieListState)  {
        movieViewModel.getRandomMovie()
    }

    LaunchedEffect(movieListState)  {
        movieViewModel.getErrorMessage()
    }
    randomMovieState?.let { details ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Box(
                modifier = Modifier
                    .weight(5f)
            ) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxSize(),
                    model = "https://image.tmdb.org/t/p/w200" + details.thumbnail,
                    contentDescription = details.thumbnail
                )

                Text(
                    details.rating.toString(),
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .align(Alignment.TopEnd)
                )
            }
            Column(
                modifier = Modifier
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
                Text(
                    randomMovieGenresState,
                    style = MaterialTheme.typography.labelLarge
                )

                details.description?.let {
                    Text(
                        it,
                        modifier = Modifier,
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
        // On Skip
        Box(
            modifier = Modifier
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(0.8f),
                onClick = onSkip
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = EvaIcons.Fill.Close,
                    contentDescription = "Skip",
                    tint = Color.White
                )
            }
        }

        // On Super Like
        Box(
            modifier = Modifier
                .weight(4f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(0.9f),
                onClick = onSuperLike
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = EvaIcons.Fill.Flash,
                    contentDescription = "Super Like",
                    tint = Color.White
                )
            }
        }

        // On Like
        Box(
            modifier = Modifier
                .weight(3f),
            contentAlignment = Alignment.Center
        ) {
            Button(
                modifier = Modifier
                    .aspectRatio(1f)
                    .fillMaxSize(0.8f),
                onClick = onLike
            ) {
                Icon(
                    modifier = Modifier
                        .fillMaxSize(),
                    imageVector = EvaIcons.Fill.Heart,
                    contentDescription = "Like",
                    tint = Color.White
                )
            }
        }
    }
}