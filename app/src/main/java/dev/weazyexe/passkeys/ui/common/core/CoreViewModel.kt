package dev.weazyexe.passkeys.ui.common.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base [ViewModel] for all the screens in the app
 */
abstract class CoreViewModel<S : State, E : Effect>() : ViewModel() {

    /**
     * UI state
     */
    private val _uiState by lazy { MutableStateFlow(initialState) }
    val uiState: StateFlow<S>
        get() = _uiState.asStateFlow()
    protected val state: S
        get() = uiState.value

    /**
     * Side effects to screen channel
     */
    private val _effects = Channel<E>(Channel.BUFFERED)
    val effects: Flow<E>
        get() = _effects.receiveAsFlow()

    /**
     * Initial screen state
     */
    protected abstract val initialState: S

    /**
     * Updates the screen state
     */
    protected fun setState(stateBuilder: S.() -> S) {
        _uiState.value = stateBuilder(uiState.value)
    }

    /**
     * Triggers side-effect
     */
    protected fun E.emit() = viewModelScope.launch {
        _effects.send(this@emit)
    }

    /**
     * Launches the flow on main thread in view model scope
     */
    protected fun Flow<*>.launchInViewModelScope() =
        this.flowOn(Dispatchers.Main).launchIn(viewModelScope)
}