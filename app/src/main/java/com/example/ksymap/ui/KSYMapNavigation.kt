package com.example.ksymap.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.room.util.query
import com.example.ksymap.ui.main.MainScreen
import com.example.ksymap.ui.map.MapScreen
import com.example.ksymap.ui.placedetail.PlaceDetailScreen
import com.example.ksymap.ui.placesearch.PlaceSearchScreen

@Composable
fun KSYMapNavigation(
    appState: KSYMapAppState,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = appState.navController,
        startDestination = KSYMapDestinations.MAP_ROUTE,
        modifier = modifier
    ) {

        composable(
            route = KSYMapDestinations.MAIN_ROUTE
        ) { backStackEntry ->
            MainScreen(
                navigateToMap = { appState.navigateToMap(backStackEntry) }
            )
        }

        composable(
            route = KSYMapDestinations.MAP_ROUTE
        ) { backStackEntry ->
            MapScreen(
                appState = appState,
                navigateToPlaceSearch = { appState.navigateToPlaceSearch(backStackEntry) }
            )
        }

        composable(
            route = KSYMapDestinations.PLACE_SEARCH_ROUTE
        ) { backStackEntry ->
            PlaceSearchScreen(
                appState = appState,
                navigateToPlaceDetail = { query -> appState.navigateToPlaceSearch(query, backStackEntry) }
            )
        }

        composable(
            route = "main/map/search/detail/{query}",
            arguments = listOf(navArgument("query") { type = NavType.StringType })
        ) { backStackEntry ->
            val query = backStackEntry.arguments?.getString("query") ?: ""
            PlaceDetailScreen(
                appState = appState,
                query = query
            )
        }

    }

}