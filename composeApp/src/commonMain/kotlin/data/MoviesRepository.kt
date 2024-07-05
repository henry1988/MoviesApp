package data

import MoviesComponent
import app.moviebase.tmdb.Tmdb3
import app.moviebase.tmdb.model.TmdbMoviePageResult

class MoviesRepository(
    private val tmdbApi: Tmdb3 = MoviesComponent.tmdbApi
) {

    suspend fun getPopularMovies(): TmdbMoviePageResult {

        val result: TmdbMoviePageResult = tmdbApi.discover.discoverMovie(
            page = 1, language = "en-US", parameters = hashMapOf(
                "include_adult" to "false",
                "include_video" to "false",
                "sort_by" to "popularity.desc"
            )
        )

        return result

    }
}
