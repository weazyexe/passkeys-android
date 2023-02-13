package dev.weazyexe.passkeys.ui.screens.auth

import androidx.compose.runtime.Composable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

private const val ROUTE = "/auth"

@RootNavGraph(start = true)
@Destination(
    route = ROUTE
)
@Composable
fun AuthScreen() {

}