package dev.weazyexe.passkeys.ui.screens.auth

import androidx.annotation.StringRes
import androidx.credentials.CreatePublicKeyCredentialRequest
import dev.weazyexe.passkeys.ui.common.core.Effect
import dev.weazyexe.passkeys.ui.common.core.State

data class AuthState(
    val login: String = ""
) : State

sealed class AuthEffect : Effect {

    data class OpenCredentialsDialog(val request: CreatePublicKeyCredentialRequest) : AuthEffect()
    data class ShowError(@StringRes val message: Int) : AuthEffect()
}