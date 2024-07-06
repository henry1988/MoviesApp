package com.hmartinez.movie.di

import com.hmartinez.movie.data.MoviesRepository
import com.hmartinez.movie.data.MoviesRepositoryImpl
import com.hmartinez.movie.domain.usecases.GetMovieDetailUseCase
import com.hmartinez.movie.domain.usecases.GetPopularMoviesUseCase
import org.koin.dsl.module

fun appModule() = module {
    single<MoviesRepository> { MoviesRepositoryImpl() }
    factory { GetPopularMoviesUseCase(get()) }
    factory { GetMovieDetailUseCase(get()) }
}