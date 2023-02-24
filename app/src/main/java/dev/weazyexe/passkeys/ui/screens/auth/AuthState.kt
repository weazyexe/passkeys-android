package dev.weazyexe.passkeys.ui.screens.auth

import dev.weazyexe.passkeys.ui.common.core.Effect
import dev.weazyexe.passkeys.ui.common.core.State

data class AuthState(
    val login: String = ""
) : State

sealed class AuthEffect : Effect