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
import coil3.compose.AsyncImage
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.movie.bucket.core.data.models.Cocktail
import org.movie.bucket.core.data.models.Movie
import org.movie.bucket.core.data.network.CocktailClient
import org.movie.bucket.core.data.network.InsultCensorClient
import org.movie.bucket.core.data.network.MovieClient
import org.movie.bucket.utility.onError
import org.movie.bucket.utility.onSuccess
import util.NetworkError


@Composable
@Preview
fun App(
    insultClient: InsultCensorClient,
    cocktailClient: CocktailClient,
    movieClient: MovieClient,
) {
    MaterialTheme {
//        HomeScreen()
//            var censoredText by remember {
//                mutableStateOf<String?>(null)
//            }
        var movie by remember {
            mutableStateOf<Movie?>(null)
        }
        var cocktailDrink by remember {
            mutableStateOf<Cocktail?>(null)
        }
        var cocktailName by remember {
            mutableStateOf<String?>(null)
        }
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
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            TextField(
                value = uncensoredText,
                onValueChange = { uncensoredText = it },
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
                    errorMessage = null

//                        insultClient.censorWords(uncensoredText)
//                            .onSuccess {
//                                censoredText = it
//                            }
//                            .onError {
//                                errorMessage = it
//                            }

//                    cocktailClient.getRandomCocktail()
//                        .onSuccess { cocktail ->
//                            cocktailName = cocktail.first().toString()
//                            cocktailDrink = cocktail.first()
//                        }
//                        .onError {
//                            errorMessage = it
//                        }
                    movieClient.getPopularMovies()
                        .onSuccess { movieList ->
                            movie = movieList.random()
                        }
                        .onError {
                            errorMessage = it
                        }
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
//                censoredText?.let {
//                    Text(it)
//                }
//            cocktailDrink?.let { details ->
            movie?.let { details ->
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
//            cocktailName?.let {
//                Text(it)
//            }
            errorMessage?.let {
                Text(
                    text = it.name,
                    color = Color.Red
                )
            }
        }

    }
}