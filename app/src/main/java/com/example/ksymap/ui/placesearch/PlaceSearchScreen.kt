package com.example.ksymap.ui.placesearch

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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.ksymap.R
import com.example.ksymap.ui.KSYMapAppState
import kotlinx.coroutines.launch
import com.example.ksymap.ui.placesearch.PlaceSearchContract.*
import com.example.ksymap.ui.theme.Gray500
import com.example.ksymap.ui.theme.Gray600
import com.example.ksymap.ui.theme.Gray700
import com.example.ksymap.ui.theme.KSYTextStyles
import com.example.ksymap.ui.theme.Purple500
import kotlinx.coroutines.flow.collectLatest

@Composable
fun PlaceSearchScreen(
    appState: KSYMapAppState,
    viewModel: PlaceSearchViewModel = hiltViewModel()
) {
    val viewState by viewModel.viewState.collectAsState()
    val scope = rememberCoroutineScope()

    PlaceSearchView(
        viewState = viewState,
        onClickBackIcon = {
            viewModel.setEvent(PlaceSearchEvent.OnClickBackIcon)
        },
        onClickSearchBar = {
            viewModel.setEvent(PlaceSearchEvent.OnClickSearchBar)
        },
        onSearch = { query ->
            viewModel.setEvent(PlaceSearchEvent.OnSearch(query))
        },
        modifier = Modifier
    )

    LaunchedEffect(key1 = viewModel.effect) {
        viewModel.effect.collectLatest { effect ->
            when (effect) {
                is PlaceSearchSideEffect.NavigateUp -> {
                    appState.upPress()
                }
                is PlaceSearchSideEffect.ShowSnackBar -> {
                    scope.launch {
                        appState.showSnackbarMessage(effect.resId)
                    }
                }
            }
        }
    }

    LaunchedEffect(key1 = true) {
        viewModel.setEvent(PlaceSearchEvent.InitScreen)
    }
}

@Composable
private fun PlaceSearchView(
    viewState: PlaceSearchViewState,
    onClickBackIcon: () -> Unit,
    onClickSearchBar: () -> Unit,
    onSearch: (String) -> Unit,
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
            PlaceSearchTopBarView(
                onClickBackIcon,
                onClickSearchBar,
                onSearch,
                modifier = modifier
            )
            Spacer(modifier = modifier.weight(1f))
        }
    }
}

@Composable
fun PlaceSearchTopBarView(
    onClickBackIcon: () -> Unit,
    onClickSearchBar: () -> Unit,
    onSearch: (String) -> Unit,
    modifier: Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp)
            .padding(start = 16.dp)
    ) {

        Row(
            modifier = modifier
                .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = modifier
                    .weight(1f)
            ) {
                SearchTextFieldView(
                    onSearch,
                    modifier = modifier
                )
            }
            Box(
                modifier = modifier
                    .size(56.dp)
                    .clickable {
                        onClickBackIcon()
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Filled.Close,
                    contentDescription = "close_icon",
                    modifier = modifier.size(24.dp),
                    tint = Gray700
                )
            }
        }


    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SearchTextFieldView(
    onSearch: (query: String) -> Unit,
    modifier: Modifier
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    var queryValue by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    OutlinedTextField(
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        value = queryValue,
        onValueChange = {
            queryValue = it
        },
        singleLine = true,
        placeholder = {
            Text(
                text = stringResource(id = R.string.map_search_placeholder),
                style = KSYTextStyles.captionRegular(Gray600)
            )
        },
        keyboardActions = KeyboardActions(onSearch = {
            onSearch(queryValue)
            keyboardController?.hide()
        }),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple500,
            unfocusedBorderColor = Gray500,
            cursorColor = Purple500
        ),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search)
    )

}