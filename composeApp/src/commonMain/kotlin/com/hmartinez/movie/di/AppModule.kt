package com.hmartinez.movie.di

import com.hmartinez.movie.data.MoviesRepository
import com.hmartinez.movie.data.MoviesRepositoryImpl
import com.hmartinez.movie.domain.GetPopularMoviesUseCase
import com.hmartinez.movie.presentation.MovieListViewModel
import org.koin.dsl.module

fun appModule() = module {
    single<MoviesRepository> { MoviesRepositoryImpl() }
    factory { GetPopularMoviesUseCase(get()) }

}