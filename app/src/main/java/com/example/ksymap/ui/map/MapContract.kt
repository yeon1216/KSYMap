package com.example.ksymap.ui.map

import com.example.ksymap.ui.base.KSYLoadState
import com.example.ksymap.ui.base.ViewEvent
import com.example.ksymap.ui.base.ViewSideEffect
import com.example.ksymap.ui.base.ViewState

class MapContract {

    data class MapViewState(
        val loadState: KSYLoadState = KSYLoadState.SUCCESS,
        val search: String = "",
        val error: Throwable? = null,
        val isSearchFocused: Boolean = false
    ) : ViewState

    sealed class MapSideEffect: ViewSideEffect {
        object NavigateUp : MapSideEffect()
        data class ShowSnackBar(val resId: Int) : MapSideEffect()
    }

    sealed class MapEvent: ViewEvent {
        object InitScreen: MapEvent()
        data class OnSearch(val query: String): MapEvent()
        object OnClickSearchBar: MapEvent()
        object OnClickBackIcon: MapEvent()
    }

}