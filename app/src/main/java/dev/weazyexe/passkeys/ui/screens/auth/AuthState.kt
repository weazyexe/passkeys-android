package dev.weazyexe.passkeys.ui.screens.auth

import androidx.credentials.CreatePublicKeyCredentialRequest
import dev.weazyexe.passkeys.ui.common.core.Effect
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.ui.common.core.State

data class AuthState(
    val login: String = "",
    val accountCreationLoadState: LoadState<Boolean> = LoadState.empty()
) : State

sealed class AuthEffect : Effect {

    data class OpenCredentialsDialog(val request: CreatePublicKeyCredentialRequest) : AuthEffect()
    object OpenMainScreen : AuthEffect()
}