package org.movie.bucket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel
import org.movie.bucket.data.network.CocktailClient
import org.movie.bucket.data.network.KtorMovieClient
import org.movie.bucket.presentation.features.home.HomeViewModel
import util.NetworkError

@Composable
@Preview
fun App(
    cocktailClient: CocktailClient,
    ktorMovieClient: KtorMovieClient,
    homeViewModel: HomeViewModel = koinViewModel()
) {
    val movieListState by homeViewModel.movieList.collectAsStateWithLifecycle()
    val randomMovieState by homeViewModel.randomMovie.collectAsStateWithLifecycle()

    var uncensoredText by remember {
        mutableStateOf("")
    }
    var isLoading by remember {
        mutableStateOf(false)
    }
    var errorMessage by remember {
        mutableStateOf<NetworkError?>(null)
    }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit)  {
        homeViewModel.getPopularMovies()
    }

    LaunchedEffect(movieListState)  {
        homeViewModel.getRandomMovie()
    }

    LaunchedEffect(movieListState)  {
        homeViewModel.getErrorMessage()
    }

    MaterialTheme {
//        HomeScreen()
//            var censoredText by remember {
//                mutableStateOf<String?>(null)
//            }
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            TextField(
                value = uncensoredText,
                onValueChange = { userInput ->
                    uncensoredText = userInput
                },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth(),
                placeholder = {
                    Text("Uncensored text")
                }
            )
            Button(onClick = {
                scope.launch {
                    isLoading = true
                    homeViewModel.getRandomMovie()
                    isLoading = false
                }
            }) {
                if(isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(15.dp),
                        strokeWidth = 1.dp,
                        color = Color.White
                    )
                } else {
                    Text("Censor!")
                }
            }
            randomMovieState?.let { details ->
                Column {
                    AsyncImage(
                        model = "https://image.tmdb.org/t/p/w200" + details.thumbnail,
                        contentDescription = details.thumbnail
                    )
                    Spacer(Modifier.height(16.dp))
                    Text(details.title)
                    Spacer(Modifier.height(16.dp))
                    details.description?.let { Text(it) }
                    Spacer(Modifier.height(16.dp))
                    details.rating?.let { Text(it.toString()) }
                    Spacer(Modifier.height(16.dp))
                    Text(details.language)
                }
            }
            errorMessage?.let {
                Text(
                    text = it.name,
                    color = Color.Red
                )
            }
        }

    }
}