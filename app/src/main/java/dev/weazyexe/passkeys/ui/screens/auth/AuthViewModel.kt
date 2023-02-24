package dev.weazyexe.passkeys.ui.screens.auth

import androidx.credentials.CreatePublicKeyCredentialResponse
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.weazyexe.passkeys.app.error.ResponseError
import dev.weazyexe.passkeys.data.repository.AuthRepository
import dev.weazyexe.passkeys.ui.common.core.CoreViewModel
import dev.weazyexe.passkeys.utils.flowIo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : CoreViewModel<AuthState, AuthEffect>() {

    override val initialState: AuthState = AuthState()

    fun updateLogin(login: String) {
        setState { copy(login = login) }
    }

    fun startRegistration() {
        authRepository.signIn(state.login)
            .flowIo()
            .onEach { AuthEffect.OpenCredentialsDialog(it).emit() }
            .catch { AuthEffect.ShowError((it as ResponseError).errorMessage).emit() }
            .flowOn(Dispatchers.Main)
            .launchIn(viewModelScope)
    }

    fun sendRegistrationResponse(response: CreatePublicKeyCredentialResponse) {

    }

    fun signUp() {

    }
}