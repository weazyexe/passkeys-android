package dev.weazyexe.passkeys.ui.screens.main.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import dev.weazyexe.passkeys.domain.Credential
import dev.weazyexe.passkeys.ui.common.core.LoadState

@Composable
fun ColumnScope.CredentialsComponent(
    credentialsLoadState: LoadState<List<Credential>>
) {
    val context = LocalContext.current
    when {
        credentialsLoadState.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        credentialsLoadState.error != null -> {
            Text(text = credentialsLoadState.error.asLocalizedMessage(context))
        }
        credentialsLoadState.data != null -> {

        }
    }
}