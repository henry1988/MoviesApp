package com.hmartinez.movie.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.hmartinez.movie.domain.usecases.GetPopularMoviesUseCase
import com.hmartinez.movie.domain.models.Movie

class MoviesPagingSource(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    PagingSource<Int, Movie>() {
    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        try {
            val nextPageNumber = params.key ?: 1
            val response = getPopularMoviesUseCase.invoke(nextPageNumber)

            return LoadResult.Page(
                data = response.movies,
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


