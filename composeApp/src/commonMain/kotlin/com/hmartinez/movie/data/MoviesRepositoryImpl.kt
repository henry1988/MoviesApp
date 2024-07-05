package com.hmartinez.movie.data

import MoviesComponent
import app.moviebase.tmdb.Tmdb3
import app.moviebase.tmdb.model.TmdbMoviePageResult
import com.hmartinez.movie.domain.models.MoviesPage

class MoviesRepositoryImpl(
    private val tmdbApi: Tmdb3 = MoviesComponent.tmdbApi
) : MoviesRepository {

    override suspend fun getPopularMovies(page: Int): MoviesPage {

        val result: TmdbMoviePageResult = tmdbApi.discover.discoverMovie(
            page = page, language = "en-US", parameters = hashMapOf(
                "include_adult" to "false",
                "include_video" to "false",
                "sort_by" to "popularity.desc"
            )
        )

        return toMoviePage(result)

    }
}