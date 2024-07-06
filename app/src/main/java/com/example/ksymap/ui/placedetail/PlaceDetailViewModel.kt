package com.example.ksymap.ui.placedetail
import com.example.ksymap.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ksymap.ui.placedetail.PlaceDetailContract.*

@HiltViewModel
class PlaceDetailViewModel @Inject constructor() : BaseViewModel<PlaceDetailViewState, PlaceDetailSideEffect, PlaceDetailEvent>(
    PlaceDetailViewState()
) {
    override fun handleEvents(event: PlaceDetailEvent) {
        when (event) {
            is PlaceDetailEvent.InitScreen -> {

            }
            is PlaceDetailEvent.OnClickBackIcon -> {
                sendEffect({ PlaceDetailSideEffect.NavigateUp })
            }
            else -> {}
        }
    }
}
