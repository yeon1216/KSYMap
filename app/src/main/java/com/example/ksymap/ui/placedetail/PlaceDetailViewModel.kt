package com.example.ksymap.ui.placedetail
import androidx.lifecycle.viewModelScope
import com.example.ksymap.data.onError
import com.example.ksymap.data.onSuccess
import com.example.ksymap.data.repository.PlaceRepository
import com.example.ksymap.ui.base.BaseViewModel
import com.example.ksymap.ui.base.KSYLoadState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.example.ksymap.ui.placedetail.PlaceDetailContract.*
import kotlinx.coroutines.launch

@HiltViewModel
class PlaceDetailViewModel @Inject constructor(
    private val placeRepository: PlaceRepository
) : BaseViewModel<PlaceDetailViewState, PlaceDetailSideEffect, PlaceDetailEvent>(PlaceDetailViewState()) {
    override fun handleEvents(event: PlaceDetailEvent) {
        when (event) {
            is PlaceDetailEvent.InitScreen -> {

            }
            is PlaceDetailEvent.OnSearch -> {
                searchPlaces(event.query)
            }
            is PlaceDetailEvent.OnClickBackIcon -> {
                sendEffect({ PlaceDetailSideEffect.NavigateUp })
            }
            else -> {}
        }
    }

    private fun searchPlaces(query: String) = viewModelScope.launch {
        updateState { copy(loadState = KSYLoadState.LOADING) }
        val result = placeRepository.searchPlaces(query)
        result
            .onSuccess { response ->
                println("ksy : searchPoiInfo : ${response.searchPoiInfo}")
                println("ksy : searchPoiInfo : ${response.searchPoiInfo.pois.poi}")
                updateState { copy(loadState = KSYLoadState.SUCCESS) }
            }
            .onError { error ->
                println("ksy : error : $error")
                updateState { copy(loadState = KSYLoadState.ERROR) }
            }
    }

}
