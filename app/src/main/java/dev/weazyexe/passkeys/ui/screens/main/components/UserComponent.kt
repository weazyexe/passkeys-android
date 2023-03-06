package dev.weazyexe.passkeys.ui.screens.main.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.weazyexe.passkeys.R
import dev.weazyexe.passkeys.app.error.ResponseError
import dev.weazyexe.passkeys.domain.User
import dev.weazyexe.passkeys.ui.common.core.LoadState
import dev.weazyexe.passkeys.ui.common.preview.PasskeysViewPreview
import dev.weazyexe.passkeys.ui.common.preview.PreviewContent

@Composable
fun ColumnScope.UserComponent(
    userLoadState: LoadState<User>
) {
    val context = LocalContext.current
    when {
        userLoadState.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.padding(16.dp))
        }
        userLoadState.error != null -> {
            Text(text = userLoadState.error.asLocalizedMessage(context))
        }
        userLoadState.data != null -> {
            Text(
                text = stringResource(id = R.string.main_user_greeting, userLoadState.data.name),
                style = MaterialTheme.typography.displayMedium
            )
        }
    }
}

@PasskeysViewPreview
@Composable
private fun UserComponentDataPreview() {
    PreviewContent {
        Column {
            UserComponent(userLoadState = LoadState.data(User.mock()))
        }
    }
}

@PasskeysViewPreview
@Composable
private fun UserComponentErrorPreview() {
    PreviewContent {
        Column {
            UserComponent(userLoadState = LoadState.error(ResponseError.UserNotFound()))
        }
    }
}