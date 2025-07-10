package org.movie.bucket

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.movie.bucket.data.InsultCensorClient
import org.movie.bucket.domain.utility.onError
import org.movie.bucket.domain.utility.onSuccess
import org.movie.bucket.presentation.features.home.HomeScreen
import util.NetworkError


@Composable
@Preview
fun App(client: InsultCensorClient) {
    MaterialTheme {
//        HomeScreen()
            var censoredText by remember {
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

                        client.censorWords(uncensoredText)
                            .onSuccess {
                                censoredText = it
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
                censoredText?.let {
                    Text(it)
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