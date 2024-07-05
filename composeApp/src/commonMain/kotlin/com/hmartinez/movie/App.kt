package com.hmartinez.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import app.moviebase.tmdb.model.TmdbMovie
import com.hmartinez.movie.di.appModule
import com.hmartinez.movie.di.viewmodelModule
import org.jetbrains.compose.ui.tooling.preview.Preview

import com.hmartinez.movie.presentation.MovieListViewModel
import org.koin.compose.KoinApplication
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule(), viewmodelModule())
    }) {
        MaterialTheme {
            var showContent by remember { mutableStateOf(false) }
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                MovieListHome()
            }
        }
    }

}

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieListHome() {
    val viewModel: MovieListViewModel = koinViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val tmdbMovieLazyPagingItems = viewModel.flow.collectAsLazyPagingItems()


    //create lazy column in compose
    LazyColumn {
        if (tmdbMovieLazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(count = tmdbMovieLazyPagingItems.itemCount) { index ->
            val item: TmdbMovie? = tmdbMovieLazyPagingItems[index]
            Text("Index=$index: ${item?.title}", fontSize = 20.sp)
        }

        if (tmdbMovieLazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
