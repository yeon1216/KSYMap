package com.example.ksymap.ui.map

import android.graphics.PointF
import android.os.Bundle
import android.view.View
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.ksymap.R
import com.example.ksymap.ui.KSYMapAppState
import kotlinx.coroutines.launch
import com.example.ksymap.ui.map.MapContract.*
import com.example.ksymap.ui.theme.*
import com.example.ksymap.ui.theme.Gray700
import com.example.ksymap.ui.theme.KSYTextStyles
import com.example.ksymap.ui.theme.Shapes
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.MapView
import com.naver.maps.map.NaverMap
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MapScreen(
    appState: KSYMapAppState,
    navigateToPlaceSearch: () -> Unit,
    viewModel: MapViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val scope = rememberCoroutineScope()

    MapView(
        viewState = viewState,
        onClickBackIcon = {
            viewModel.setEvent(MapEvent.OnClickBackIcon)
        },
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
                is MapSideEffect.NavigateToSearch -> {
                    navigateToPlaceSearch()
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
    onClickBackIcon: () -> Unit,
    onClickSearchBar: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        NaverMapComposable()
        Column(
            modifier = modifier
                .padding(top = 16.dp)
        ) {
            MapTopBarView(
                search = viewState.search,
                onClickBackIcon,
                onClickSearchBar,
                modifier = modifier
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun MapTopBarView(
    search: String,
    onClickBackIcon: () -> Unit,
    onClickSearchBar: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(end = 16.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clickable {
                        onClickBackIcon()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "arrow_icon",
                    modifier = modifier.size(24.dp),
                    tint = Gray700
                )
            }
            Box(
                modifier = modifier
                    .weight(1f)
                    .background(White)
                    .clickable {
                        onClickSearchBar()
                    }
            ) {
                SearchBarView(
                    search = search
                )
            }
        }


    }
}

@Composable
fun SearchBarView(
    search: String,
    modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier.fillMaxWidth()
        ) {
            Box(
                modifier = modifier,
                contentAlignment = Alignment.CenterStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .border(
                            BorderStroke(1.dp, Color.Gray),
                            shape = Shapes.medium
                        )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {
                        if (search == "") {
                            Text(
                                text = stringResource(id = R.string.map_search_placeholder),
                                style = KSYTextStyles.captionRegular(Gray600)
                            )
                        } else {
                            Text(
                                text = search,
                                style = KSYTextStyles.subTitle2Regular(Black)
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "search_icon",
                            modifier = modifier
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun NaverMapComposable() {
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val coroutineScope = rememberCoroutineScope()

    // Create a remember variable for MapView
    val mapView = remember {
        MapView(context).apply {
            // Initialize the MapView
            onCreate(Bundle())

            // Set up lifecycle observer
            getMapAsync { naverMap ->
                // Handle map setup asynchronously if needed
            }
        }
    }

    // LifecycleEventObserver to handle lifecycle events
    val lifecycleObserver = remember {
        LifecycleEventObserver { _, event ->
            coroutineScope.launch {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> mapView.onCreate(Bundle())
                    Lifecycle.Event.ON_START -> mapView.onStart()
                    Lifecycle.Event.ON_RESUME -> mapView.onResume()
                    Lifecycle.Event.ON_PAUSE -> mapView.onPause()
                    Lifecycle.Event.ON_STOP -> mapView.onStop()
                    Lifecycle.Event.ON_DESTROY -> mapView.onDestroy()
                    // Handle other lifecycle events if needed
                    else -> Unit
                }
            }
        }
    }

    // Add lifecycle observer and remove it when Composable is disposed
    DisposableEffect(Unit) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }

    // Wrap the MapView in AndroidView and return it
    AndroidView(factory = { mapView })
}