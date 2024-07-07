package com.example.ksymap.ui.placedetail

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
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ksymap.R
import com.example.ksymap.ui.KSYMapAppState
import kotlinx.coroutines.launch
import com.example.ksymap.ui.placedetail.PlaceDetailContract.*
import com.example.ksymap.ui.theme.Black
import com.example.ksymap.ui.theme.Gray500
import com.example.ksymap.ui.theme.Gray600
import com.example.ksymap.ui.theme.Gray700
import com.example.ksymap.ui.theme.KSYTextStyles
import com.example.ksymap.ui.theme.Purple500
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlaceDetailScreen(
    appState: KSYMapAppState,
    query: String,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val scope = rememberCoroutineScope()

    PlaceDetailView(
        viewState = viewState,
        onClickBackIcon = {
            viewModel.setEvent(PlaceDetailEvent.OnClickBackIcon)
        },
        query = query,
        modifier = Modifier
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaceDetailSideEffect.NavigateUp -> {
                    appState.upPress()
                }
                is PlaceDetailSideEffect.ShowSnackBar -> {
                    scope.launch {
                        appState.showSnackbarMessage(effect.resId)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(PlaceDetailEvent.InitScreen)
        viewModel.setEvent(PlaceDetailEvent.OnSearch(query = query))
    }
}

@Composable
private fun PlaceDetailView(
    viewState: PlaceDetailViewState,
    query: String,
    onClickBackIcon: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = modifier
                .padding(top = 16.dp)
        ) {
            PlaceDetailTopBarView(
                query = query,
                onClickBackIcon,
                modifier = modifier
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun PlaceDetailTopBarView(
    query: String,
    onClickBackIcon: () -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
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
            Spacer(modifier = modifier.weight(1f))
        }

        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = modifier.weight(1f))
            Text(
                text = query,
                style = KSYTextStyles.subTitle1Bold(Black),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = modifier.weight(1f))
        }


    }
}
