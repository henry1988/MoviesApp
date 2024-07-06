package com.hmartinez.movie.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.hmartinez.movie.data.MoviesPagingSource
import com.hmartinez.movie.domain.usecases.GetPopularMoviesUseCase
import com.hmartinez.movie.presentation.MovieListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MovieListViewModel(private val getPopularMoviesUseCase: GetPopularMoviesUseCase) :
    ViewModel() {
    private val _uiState = MutableStateFlow(MovieListUiState(emptyList()))
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    val flow = Pager(
        PagingConfig(pageSize = 20)
    ) {
        MoviesPagingSource(getPopularMoviesUseCase)
    }.flow
        .cachedIn(viewModelScope)

    init {


    }


}
