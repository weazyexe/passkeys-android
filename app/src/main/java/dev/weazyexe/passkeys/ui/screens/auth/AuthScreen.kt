package dev.weazyexe.passkeys.ui.screens.auth

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

private const val ROUTE = "/auth"

@RootNavGraph(start = true)
@Destination(route = ROUTE)
@Composable
fun AuthScreen() {
    val viewModel = hiltViewModel<AuthViewModel>()
    val state by viewModel.uiState.collectAsState()

    AuthBody(
        login = state.login,
        onLoginChange = viewModel::updateLogin,
        onSignInClick = viewModel::signIn,
        onSignUpClick = viewModel::signUp
    )
}