package dev.weazyexe.passkeys.ui.screens.main

import dagger.hilt.android.lifecycle.HiltViewModel
import dev.weazyexe.passkeys.app.error.ResponseError
import dev.weazyexe.passkeys.data.repository.user.UserRepository
import dev.weazyexe.passkeys.ui.common.core.CoreViewModel
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.utils.io
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userRepository: UserRepository
) : CoreViewModel<MainState, MainEffect>() {

    override val initialState: MainState = MainState()

    init {
        getUserFromLocalStorage()
    }

    private fun getUserFromLocalStorage() {
        userRepository.getUser()
            .io()
            .onStart { setState { copy(userLoadState = LoadState.loading()) } }
            .onEach { setState { copy(userLoadState = LoadState.data(it)) } }
            .catch { setState { copy(userLoadState = LoadState.error(it as ResponseError)) } }
            .launchInViewModelScope()
    }
}