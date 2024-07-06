package com.hmartinez.movie.domain.usecases

import com.hmartinez.movie.data.MoviesRepository
import com.hmartinez.movie.domain.models.Movie

class GetMovieDetailUseCase(private val moviesRepository: MoviesRepository) {
    suspend operator fun invoke(id: Int): Movie {
        return moviesRepository.getMovieDetail(id)
    }
}
