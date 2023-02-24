package dev.weazyexe.passkeys.ui.screens.auth

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.weazyexe.passkeys.ui.common.core.CoreViewModel
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor() : CoreViewModel<AuthState, AuthEffect>() {

    override val initialState: AuthState = AuthState()

    fun updateLogin(login: String) {
        setState { copy(login = login) }
    }

    fun signIn() {

    }

    fun signUp() {

    }
}