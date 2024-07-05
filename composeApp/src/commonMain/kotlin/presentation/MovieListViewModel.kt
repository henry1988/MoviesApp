package presentation

import MoviesComponent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import data.MoviesPagingSource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MovieListViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(MovieListUiState(emptyList()))
    val uiState: StateFlow<MovieListUiState> = _uiState.asStateFlow()

    val flow = Pager(
        // Configure how data is loaded by passing additional properties to
        // PagingConfig, such as prefetchDistance.
        PagingConfig(pageSize = 20)
    ) {
        MoviesPagingSource(MoviesComponent.tmdbApi)
    }.flow
        .cachedIn(viewModelScope)

   init {


    }


}
