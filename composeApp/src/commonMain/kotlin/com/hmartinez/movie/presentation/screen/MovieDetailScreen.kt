package com.hmartinez.movie.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hmartinez.movie.domain.models.Movie
import com.hmartinez.movie.presentation.viewmodels.MovieDetailUiState
import com.hmartinez.movie.presentation.viewmodels.MovieDetailViewModel
import io.kamel.image.KamelImage
import io.kamel.image.asyncPainterResource
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf


@OptIn(KoinExperimentalAPI::class)
@Composable
fun MovieDetailScreen(movieId: Int) {
    val viewModel: MovieDetailViewModel = koinViewModel<MovieDetailViewModel> {
        parametersOf(movieId)
    }

    val uiState by viewModel.uiState.collectAsState()


    when (uiState) {
        is MovieDetailUiState.Loading -> {
            CircularProgressIndicator()
        }

        is MovieDetailUiState.Success -> {
            val movie = (uiState as MovieDetailUiState.Success).movie

            MovieDetailScreen(movie)
        }

        is MovieDetailUiState.Error -> {
            val errorMessage = (uiState as MovieDetailUiState.Error).message
            Text(text = errorMessage)
        }
    }
}

@Composable
fun MovieDetailScreen(movie: Movie) {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(scrollState)
    ) {
        // Movie Poster
        val painterResource = asyncPainterResource(data = movie.poster)
        KamelImage(
            resource = painterResource,
            contentDescription = movie.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(2 / 3f)
        )

        // Movie Title
        Text(
            text = movie.name,
            style = MaterialTheme.typography.h5,
            modifier = Modifier.padding(top = 16.dp)
        )

        // Movie Details
        Column(modifier = Modifier.padding(top = 8.dp)) {
            DetailItem("Release Date:", movie.releaseDate)
            DetailItem("Genres:", movie.genres.joinToString(", "))
            DetailItem("Languages:", movie.languages.joinToString(", "))
            DetailItem("Popularity:", movie.popularity.toString())
            DetailItem("Vote Average:", movie.voteAverage.toString())
        }

        // Overview
        Text(
            text = "Overview",
            style = MaterialTheme.typography.h6,
            modifier = Modifier.padding(top = 16.dp)
        )
        Text(
            text = movie.overview,
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun DetailItem(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            style = MaterialTheme.typography.body2,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = value, style = MaterialTheme.typography.body2)
    }
}