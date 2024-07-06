package com.example.ksymap.ui.placesearch

import com.example.ksymap.ui.base.KSYLoadState
import com.example.ksymap.ui.base.ViewEvent
import com.example.ksymap.ui.base.ViewSideEffect
import com.example.ksymap.ui.base.ViewState

class PlaceSearchContract {

    data class PlaceSearchViewState(
        val loadState: KSYLoadState = KSYLoadState.SUCCESS,
        val search: String = "",
        val error: Throwable? = null,
        val isSearchFocused: Boolean = false
    ) : ViewState

    sealed class PlaceSearchSideEffect: ViewSideEffect {
        object NavigateUp : PlaceSearchSideEffect()
        data class ShowSnackBar(val resId: Int) : PlaceSearchSideEffect()
        data class NavigateToPlaceDetail(val query: String) : PlaceSearchSideEffect()
    }

    sealed class PlaceSearchEvent: ViewEvent {
        object InitScreen: PlaceSearchEvent()
        data class OnSearch(val query: String): PlaceSearchEvent()
        object OnClickBackIcon: PlaceSearchEvent()
    }

}