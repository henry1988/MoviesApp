package com.hmartinez.movie.domain.models

data class Movie(
    val name: String,
    val poster: String,
    val genres: List<String>,
    val overview: String,
    val popularity: String,
    val releaseDate: String,
    val languages: List<String>,
    val voteAverage: String
)