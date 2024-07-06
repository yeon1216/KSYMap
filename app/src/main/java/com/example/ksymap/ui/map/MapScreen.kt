package com.example.ksymap.ui.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ksymap.ui.KSYMapAppState
import kotlinx.coroutines.launch
import com.example.ksymap.ui.map.MapContract.*
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapScreen(
    appState: KSYMapAppState,
    viewModel: MapViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val scope = rememberCoroutineScope()

    MapView(
        viewState = viewState,
        onClickSearchBar = {
            viewModel.setEvent(MapEvent.OnClickSearchBar)
        },
        modifier = Modifier
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is MapSideEffect.NavigateUp -> {
                    appState.upPress()
                }
                is MapSideEffect.ShowSnackBar -> {
                    scope.launch {
                        appState.showSnackbarMessage(effect.resId)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(MapEvent.InitScreen)
    }
}

@Composable
private fun MapView(
    viewState: MapViewState,
    onClickSearchBar: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text("MapView")
    }
}