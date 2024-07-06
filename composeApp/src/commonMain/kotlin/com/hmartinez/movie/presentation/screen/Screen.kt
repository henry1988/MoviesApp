package com.hmartinez.movie.presentation.screen

import moviesapp.composeapp.generated.resources.Res
import moviesapp.composeapp.generated.resources.ic_my_list
import moviesapp.composeapp.generated.resources.ic_new_releases
import moviesapp.composeapp.generated.resources.ic_popular
import org.jetbrains.compose.resources.DrawableResource

sealed class Screen(val route: String, val title: String, val icon: DrawableResource) {
    object Popular : Screen("popular", "Popular", Res.drawable.ic_popular)
    object NewReleases : Screen("new_releases", "New Releases", Res.drawable.ic_new_releases)
    object MyList : Screen("my_list", "My List", Res.drawable.ic_my_list)
    object Detail : Screen("detail/{movieId}", "Movie Detail", Res.drawable.ic_my_list) {
        fun createRoute(movieId: Int) = "detail/$movieId"
    }
}