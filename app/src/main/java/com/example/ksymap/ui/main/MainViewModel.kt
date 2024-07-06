package com.example.ksymap.ui.main

import com.example.ksymap.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ksymap.ui.main.MainContract.*

@HiltViewModel
class MainViewModel @Inject constructor() : BaseViewModel<MainViewState, MainSideEffect, MainEvent>(
    MainViewState()
) {
    override fun handleEvents(event: MainEvent) {
        when (event) {
            is MainEvent.InitScreen -> {

            }
            is MainEvent.OnClickMapBtn -> {
                sendEffect({ MainSideEffect.NavigateToMap })
            }
            else -> {}
        }
    }
}
