package com.hmartinez.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import com.hmartinez.movie.di.appModule
import com.hmartinez.movie.di.viewmodelModule
import com.hmartinez.movie.domain.models.Movie
import com.hmartinez.movie.presentation.MovieListViewModel
import com.hmartinez.movie.presentation.widgets.MovieItem
import org.jetbrains.compose.ui.tooling.preview.Preview
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
    val movieLazyPagingItems = viewModel.flow.collectAsLazyPagingItems()

    LazyColumn {
        if (movieLazyPagingItems.loadState.refresh == LoadState.Loading) {
            item {
                Text(
                    text = "Waiting for items to load from the backend",
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }

        items(count = movieLazyPagingItems.itemCount) { index ->
            val item: Movie? = movieLazyPagingItems[index]
            item?.let { MovieItem(movie = it, {}) }
        }

        if (movieLazyPagingItems.loadState.append == LoadState.Loading) {
            item {
                CircularProgressIndicator(
                    modifier = Modifier.fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                )
            }
        }
    }
}
