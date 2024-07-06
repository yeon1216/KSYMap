package com.example.ksymap.ui.map
import com.example.ksymap.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ksymap.ui.map.MapContract.*

@HiltViewModel
class MapViewModel @Inject constructor() : BaseViewModel<MapViewState, MapSideEffect, MapEvent>(
    MapViewState()
) {
    override fun handleEvents(event: MapEvent) {
        when (event) {
            is MapEvent.InitScreen -> {

            }
            is MapEvent.OnClickBackIcon -> {
                sendEffect({ MapSideEffect.NavigateUp })
            }
            is MapEvent.OnClickSearchBar -> {

            }
            else -> {}
        }
    }
}
