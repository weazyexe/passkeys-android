package dev.weazyexe.passkeys.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import dev.weazyexe.passkeys.domain.Credential
import dev.weazyexe.passkeys.domain.User
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.ui.common.preview.PasskeysScreenPreview
import dev.weazyexe.passkeys.ui.screens.main.components.CredentialsComponent
import dev.weazyexe.passkeys.ui.screens.main.components.UserComponent
import dev.weazyexe.passkeys.ui.theme.PasskeysTheme

@Composable
fun MainBody(
    userLoadState: LoadState<User>,
    credentialsLoadState: LoadState<List<Credential>>
) {
    Column(
        modifier = Modifier.statusBarsPadding(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        UserComponent(userLoadState)
        CredentialsComponent(credentialsLoadState)
    }
}

@PasskeysScreenPreview
@Composable
fun MainBodyPreview() {
    PasskeysTheme {
        MainBody(
            userLoadState = LoadState.data(User.mock()),
            credentialsLoadState = LoadState.data(
                listOf(Credential.mock(), Credential.mock(), Credential.mock())
            )
        )
    }
}