package com.example.ksymap.ui.placesearch
import com.example.ksymap.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ksymap.ui.placesearch.PlaceSearchContract.*

@HiltViewModel
class PlaceSearchViewModel @Inject constructor() : BaseViewModel<PlaceSearchViewState, PlaceSearchSideEffect, PlaceSearchEvent>(
    PlaceSearchViewState()
) {
    override fun handleEvents(event: PlaceSearchEvent) {
        when (event) {
            is PlaceSearchEvent.InitScreen -> {

            }
            is PlaceSearchEvent.OnClickBackIcon -> {
                sendEffect({ PlaceSearchSideEffect.NavigateUp })
            }
            is PlaceSearchEvent.OnSearch -> {
                sendEffect({ PlaceSearchSideEffect.NavigateToPlaceDetail(event.query) })
            }
        }
    }
}
