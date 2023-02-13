package dev.weazyexe.passkeys.ui.common.components.textfield

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import dev.weazyexe.passkeys.R
import dev.weazyexe.passkeys.ui.theme.AppTypography

/**
 * Base text field for the app
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTextField(
    modifier: Modifier = Modifier,
    value: String = "",
    hint: String = "",
    onValueChange: (String) -> Unit,
    singleLine: Boolean = true,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    errorMessage: String = "",
    hasTogglePasswordButton: Boolean = false
) {
    var isPasswordVisible by rememberSaveable { mutableStateOf(false) }

    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            label = { Text(hint) },
            singleLine = singleLine,
            onValueChange = { onValueChange(it) },
            visualTransformation = if (hasTogglePasswordButton) {
                if (isPasswordVisible) {
                    VisualTransformation.None
                } else {
                    PasswordVisualTransformation()
                }
            } else {
                visualTransformation
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            isError = errorMessage.isNotBlank(),
            trailingIcon = {
                if (hasTogglePasswordButton) {
                    val image = if (isPasswordVisible) {
                        Icons.Filled.Visibility
                    } else {
                        Icons.Filled.VisibilityOff
                    }

                    IconButton(
                        onClick = {
                            isPasswordVisible = !isPasswordVisible
                        }
                    ) {
                        Icon(
                            imageVector = image,
                            contentDescription = stringResource(
                                if (isPasswordVisible) {
                                    R.string.content_description_password_eye_visible
                                    R.string.content_description_password_eye_visible
                                } else {
                                    R.string.content_description_password_eye_hidden
                                }
                            )
                        )
                    }
                }
            }
        )
        if (errorMessage.isNotBlank()) {
            Text(
                modifier = Modifier.padding(start = 16.dp, top = 4.dp),
                text = errorMessage,
                color = MaterialTheme.colorScheme.error,
                style = AppTypography.labelMedium
            )
        }
    }
}