package dev.weazyexe.passkeys.app.error

import android.content.Context
import androidx.annotation.StringRes
import dev.weazyexe.passkeys.R

/**
 * Exception wrapper
 */
sealed class ResponseError(
    @StringRes open val errorMessage: Int,
    open val arguments: Array<Any>
) : Throwable() {

    data class UnknownError(
        override val errorMessage: Int = R.string.error_unknown
    ) : ResponseError(errorMessage, emptyArray())

    data class NoInternetError(
        override val errorMessage: Int = R.string.error_no_internet
    ) : ResponseError(errorMessage, emptyArray())

    data class TimeoutError(
        override val errorMessage: Int = R.string.error_timed_out
    ) : ResponseError(errorMessage, emptyArray())

    data class HttpError(
        override val errorMessage: Int = R.string.error_http,
        override val arguments: Array<Any>
    ) : ResponseError(errorMessage, arguments)

    data class UserNotFound(
        override val errorMessage: Int = R.string.error_user_not_found
    ) : ResponseError(errorMessage, emptyArray())

    fun asLocalizedMessage(context: Context): String {
        return context.getString(errorMessage, *arguments)
    }
}