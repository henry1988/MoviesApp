package com.hmartinez.movie.presentation.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.LoadState
import app.cash.paging.compose.collectAsLazyPagingItems
import com.hmartinez.movie.domain.models.Movie
import com.hmartinez.movie.presentation.viewmodels.MovieListViewModel
import com.hmartinez.movie.presentation.widgets.MovieItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieListHome(onMovieClick: (Int) -> Unit) {
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
            item?.let { MovieItem(movie = it, onMovieClick) }
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