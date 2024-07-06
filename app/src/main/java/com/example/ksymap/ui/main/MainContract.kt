package com.example.ksymap.ui.main

import com.example.ksymap.ui.base.KSYLoadState
import com.example.ksymap.ui.base.ViewEvent
import com.example.ksymap.ui.base.ViewSideEffect
import com.example.ksymap.ui.base.ViewState


class MainContract {

    data class MainViewState(
        val loadState: KSYLoadState = KSYLoadState.SUCCESS,
        val error: Throwable? = null,
    ) : ViewState

    sealed class MainSideEffect: ViewSideEffect {
        object NavigateToMap : MainSideEffect()
    }

    sealed class MainEvent: ViewEvent {
        object InitScreen: MainEvent()
        object OnClickMapBtn: MainEvent()
    }

}