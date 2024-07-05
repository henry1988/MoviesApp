package com.hmartinez.movie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import app.moviebase.tmdb.model.TmdbMovie

class MoviesPagingSource(private val moviesRepository: MoviesRepository) :
    PagingSource<Int, TmdbMovie>() {
    override fun getRefreshKey(state: PagingState<Int, TmdbMovie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TmdbMovie> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = moviesRepository.getPopularMovies(nextPageNumber)

            return LoadResult.Page(
                data = response.results,
                prevKey = null, // Only paging forward.
                nextKey = response.page + 1
            )
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(e)
        }
    }

}


