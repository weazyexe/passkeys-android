package dev.weazyexe.passkeys.ui.screens.auth

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.LocalContext
import androidx.credentials.CreatePublicKeyCredentialResponse
import androidx.credentials.CredentialManager
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dev.weazyexe.passkeys.ui.screens.destinations.MainScreenDestination
import dev.weazyexe.passkeys.utils.ReceiveEffect
import dev.weazyexe.passkeys.utils.findActivity
import kotlinx.coroutines.launch

private const val ROUTE = "/auth"

@RootNavGraph(start = true)
@Destination(route = ROUTE)
@Composable
fun AuthScreen(
    navigator: DestinationsNavigator
) {
    val viewModel = hiltViewModel<AuthViewModel>()
    val state by viewModel.uiState.collectAsState()

    val context = LocalContext.current
    val scope = rememberCoroutineScope()

    ReceiveEffect(viewModel.effects) {
        when (this) {
            is AuthEffect.OpenCredentialsDialog -> {
                scope.launch {
                    try {
                        val credentialManager = CredentialManager.create(context)
                        val response = credentialManager.createCredential(request, context.findActivity())
                        viewModel.sendRegistrationResponse(response as CreatePublicKeyCredentialResponse)
                    } catch (e: Exception) {
                        Toast.makeText(context, e.message, Toast.LENGTH_LONG).show()
                    }
                }
            }
            is AuthEffect.OpenMainScreen -> {
                navigator.navigate(MainScreenDestination)
            }
        }
    }

    AuthBody(
        login = state.login,
        isLoading = state.accountCreationLoadState.isLoading,
        onLoginChange = viewModel::updateLogin,
        onSignInClick = viewModel::startRegistration,
        onSignUpClick = viewModel::signUp
    )
}