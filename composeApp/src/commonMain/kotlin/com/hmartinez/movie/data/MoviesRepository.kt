package com.hmartinez.movie.data

import app.moviebase.tmdb.model.TmdbMoviePageResult

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): TmdbMoviePageResult
}

