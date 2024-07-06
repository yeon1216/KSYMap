package com.example.ksymap.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.ksymap.ui.components.KSYMapSnackbar
import com.example.ksymap.ui.theme.KSYMapTheme

@Composable
fun KSYMapApp() {

    KSYMapTheme {

        val appState = rememberKSYMapAppState()

        Scaffold(
            snackbarHost = {
                SnackbarHost(
                    hostState = it,
                    modifier = Modifier.systemBarsPadding(),
                    snackbar = { snackbarData -> KSYMapSnackbar(snackbarData) }
                )
            },
            scaffoldState = appState.scaffoldState
        ) { innerPaddingModifier ->
            KSYMapNavigation(
                appState = appState,
                modifier = Modifier.padding(innerPaddingModifier)
            )
        }

    }

}