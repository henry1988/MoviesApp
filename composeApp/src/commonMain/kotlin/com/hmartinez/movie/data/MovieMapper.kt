package com.hmartinez.movie.data

import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbMoviePageResult
import com.hmartinez.movie.domain.models.Movie
import com.hmartinez.movie.domain.models.MoviesPage

fun mapTmdbMovieToMovie(tmdbMovie: TmdbMovie): Movie {
    return Movie(
        name = tmdbMovie.title,
        poster = tmdbMovie.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        genres = tmdbMovie.genresIds.map { it.toString() },
        overview = tmdbMovie.overview,
        popularity = tmdbMovie.popularity.toString(),
        releaseDate = tmdbMovie.releaseDate?.toString() ?: "",
        languages = listOf(tmdbMovie.originalLanguage),
        voteAverage = tmdbMovie.voteAverage.toString()
    )
}

fun toMoviePage(moviePageResult: TmdbMoviePageResult): MoviesPage {
    return MoviesPage(
        page = moviePageResult.page,
        movies = moviePageResult.results.map { mapTmdbMovieToMovie(it) }
    )
}