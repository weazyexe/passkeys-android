package dev.weazyexe.passkeys.ui.screens.auth

import androidx.credentials.CreatePublicKeyCredentialResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.weazyexe.passkeys.app.error.ResponseError
import dev.weazyexe.passkeys.data.repository.auth.AuthRepository
import dev.weazyexe.passkeys.data.repository.user.UserRepository
import dev.weazyexe.passkeys.ui.common.core.CoreViewModel
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.utils.io
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val userRepository: UserRepository
) : CoreViewModel<AuthState, AuthEffect>() {

    override val initialState: AuthState = AuthState()

    fun updateLogin(login: String) {
        setState { copy(login = login) }
    }

    fun startRegistration() {
        authRepository.startRegistration(state.login)
            .io()
            .onStart { setState { copy(accountCreationLoadState = LoadState.loading()) } }
            .onEach { AuthEffect.OpenCredentialsDialog(it).emit() }
            .catch { setState { copy(accountCreationLoadState = LoadState.error(it as ResponseError)) } }
            .launchInViewModelScope()
    }

    fun sendRegistrationResponse(response: CreatePublicKeyCredentialResponse) {
        authRepository.sendRegistrationResponse(response.registrationResponseJson)
            .map { userRepository.saveUser(it) }
            .io()
            .onEach { AuthEffect.OpenMainScreen.emit() }
            .catch { setState { copy(accountCreationLoadState = LoadState.error(it as ResponseError)) } }
            .launchInViewModelScope()
    }

    fun signUp() {

    }
}