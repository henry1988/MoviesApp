package com.hmartinez.movie.di

import com.hmartinez.movie.presentation.MovieListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

fun viewmodelModule() = module {
    viewModel { MovieListViewModel(get()) }
}