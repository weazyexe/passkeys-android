package dev.weazyexe.passkeys.ui.screens.main

import dev.weazyexe.passkeys.domain.Credential
import dev.weazyexe.passkeys.domain.User
import dev.weazyexe.passkeys.ui.common.core.Effect
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.ui.common.core.State

data class MainState(
    val userLoadState: LoadState<User> = LoadState.empty(),
    val credentialsLoadState: LoadState<List<Credential>> = LoadState.empty()
) : State

sealed class MainEffect : Effect