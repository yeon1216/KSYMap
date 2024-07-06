package com.example.ksymap.ui.placedetail

import com.example.ksymap.ui.base.KSYLoadState
import com.example.ksymap.ui.base.ViewEvent
import com.example.ksymap.ui.base.ViewSideEffect
import com.example.ksymap.ui.base.ViewState

class PlaceDetailContract {

    data class PlaceDetailViewState(
        val loadState: KSYLoadState = KSYLoadState.SUCCESS,
        val search: String = "검색어(실제 검색어사용)",
        val error: Throwable? = null
    ) : ViewState

    sealed class PlaceDetailSideEffect: ViewSideEffect {
        object NavigateUp : PlaceDetailSideEffect()
        data class ShowSnackBar(val resId: Int) : PlaceDetailSideEffect()
    }

    sealed class PlaceDetailEvent: ViewEvent {
        object InitScreen: PlaceDetailEvent()
        data class OnSearch(val query: String): PlaceDetailEvent()
        object OnClickSearchBar: PlaceDetailEvent()
        object OnClickBackIcon: PlaceDetailEvent()
    }

}