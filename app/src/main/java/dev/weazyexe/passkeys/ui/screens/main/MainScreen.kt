package dev.weazyexe.passkeys.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

private const val ROUTE = "/main"

@Destination(route = ROUTE)
@Composable
fun MainScreen() {
    val viewModel = hiltViewModel<MainViewModel>()
    val state by viewModel.uiState.collectAsState()

    MainBody(
        userLoadState = state.userLoadState,
        credentialsLoadState = state.credentialsLoadState
    )
}