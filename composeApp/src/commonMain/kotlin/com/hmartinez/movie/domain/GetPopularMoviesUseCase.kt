package com.hmartinez.movie.domain

import com.hmartinez.movie.data.MoviesRepository
import com.hmartinez.movie.domain.models.MoviesPage

class GetPopularMoviesUseCase(
    private val moviesRepository: MoviesRepository,
) {

    suspend fun invoke(page: Int): MoviesPage {
        return moviesRepository.getPopularMovies(page)
    }
}