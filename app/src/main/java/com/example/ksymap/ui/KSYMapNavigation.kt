package com.example.ksymap.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.ksymap.ui.main.MainScreen
import com.example.ksymap.ui.map.MapScreen
import com.example.ksymap.ui.placesearch.PlaceSearchScreen

@Composable
fun KSYMapNavigation(
    appState: KSYMapAppState,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = appState.navController,
        startDestination = KSYMapDestinations.MAIN_ROUTE,
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
        ) {
            PlaceSearchScreen(
                appState = appState
            )
        }

    }

}