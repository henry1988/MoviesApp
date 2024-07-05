package presentation

import app.moviebase.tmdb.model.TmdbMovie

data class MovieListUiState(
    val movies: List<TmdbMovie>
)
