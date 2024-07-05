package com.hmartinez.movie.data

import com.hmartinez.movie.domain.models.MoviesPage

interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): MoviesPage
}

