package com.hmartinez.movie.data

import app.moviebase.tmdb.model.TmdbMovie
import app.moviebase.tmdb.model.TmdbMovieDetail
import app.moviebase.tmdb.model.TmdbMoviePageResult
import com.hmartinez.movie.domain.models.Movie
import com.hmartinez.movie.domain.models.MoviesPage

fun mapTmdbMovieToMovie(tmdbMovie: TmdbMovie): Movie {
    return Movie(
        id = tmdbMovie.id,
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

fun toMovie(tmdbMovieDetail: TmdbMovieDetail): Movie {
    return Movie(
        id = tmdbMovieDetail.id,
        name = tmdbMovieDetail.title,
        poster = tmdbMovieDetail.posterPath?.let { "https://image.tmdb.org/t/p/w500$it" } ?: "",
        genres = tmdbMovieDetail.genres.map { it.name },
        overview = tmdbMovieDetail.overview,
        popularity = tmdbMovieDetail.popularity.toString(),
        releaseDate = tmdbMovieDetail.releaseDate?.toString() ?: "",
        languages = listOf(tmdbMovieDetail.originalLanguage),
        voteAverage = tmdbMovieDetail.voteAverage.toString()
    )
}