package com.hmartinez.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.hmartinez.movie.di.appModule
import com.hmartinez.movie.di.viewmodelModule
import com.hmartinez.movie.presentation.screen.MovieDetailScreen
import com.hmartinez.movie.presentation.screen.MovieListHome
import com.hmartinez.movie.presentation.screen.MyListScreen
import com.hmartinez.movie.presentation.screen.NewReleasesScreen
import com.hmartinez.movie.presentation.screen.Screen
import org.jetbrains.compose.resources.vectorResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinApplication

@Composable
@Preview
fun App() {
    KoinApplication(application = {
        modules(appModule(), viewmodelModule())
    }) {
        MaterialTheme {
            Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {

                val navController = rememberNavController()
                // Get current back stack entry
                val backStackEntry by navController.currentBackStackEntryAsState()
                // Get the name of the current screen

                Scaffold(
                    bottomBar = {
                        BottomNavigation {
                            val navBackStackEntry by navController.currentBackStackEntryAsState()
                            val currentDestination: NavDestination? = navBackStackEntry?.destination
                            val items = listOf(Screen.Popular, Screen.NewReleases, Screen.MyList)
                            items.forEach { screen ->
                                BottomNavigationItem(
                                    selected = currentDestination?.route == screen.route,
                                    icon = {
                                        Icon(
                                            vectorResource(screen.icon),
                                            contentDescription = null
                                        )
                                    },
                                    label = { Text(screen.route) },
                                    onClick = {
                                        navController.navigate(screen.route)
                                    }
                                )
                            }
                        }
                    }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.Popular.route
                    ) {
                        composable(Screen.Popular.route) {
                            MovieListHome() { movieId ->
                                navController.navigate(Screen.Detail.createRoute(movieId))
                            }
                        }
                        composable(Screen.NewReleases.route) {
                            NewReleasesScreen()
                        }
                        composable(Screen.MyList.route) {
                            MyListScreen()
                        }
                        composable(
                            Screen.Detail.route,
                            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
                        ) { backStackEntry ->
                            backStackEntry.arguments?.getInt("movieId")
                                ?.let { movieId -> MovieDetailScreen(movieId) }
                        }
                    }
                }

            }

        }
    }
}
