package dev.weazyexe.passkeys.ui.common.preview

import android.content.res.Configuration
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.weazyexe.passkeys.ui.theme.PasskeysTheme

@Preview(
    name = "Light theme / Mobile",
    device = Devices.PIXEL_3A,
    showBackground = true,
    showSystemUi = true
)
@Preview(
    name = "Dark theme / Mobile",
    device = Devices.PIXEL_3A,
    showBackground = true,
    showSystemUi = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class PasskeysScreenPreview

@Preview(
    name = "Light theme",
    showBackground = true,
)
@Preview(
    name = "Dark theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class PasskeysViewPreview

@Composable
fun PreviewContent(content: @Composable () -> Unit) {
    PasskeysTheme {
        Surface {
            content()
        }
    }
}