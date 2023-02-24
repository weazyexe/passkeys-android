package dev.weazyexe.passkeys.utils

import android.util.Log
import dev.weazyexe.passkeys.app.error.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Flow builder on IO thread with error mapping
 */
fun <T> Flow<T>.flowIo() = this
    .catch {
        throw when (it) {
            is UnknownHostException,
            is ConnectException -> ResponseError.NoInternetError()
            is TimeoutException -> ResponseError.TimeoutError()
            else -> {
                Log.e("REKO", "Unknown error", it)
                UnknownError()
            }
        }
    }
    .flowOn(Dispatchers.IO)