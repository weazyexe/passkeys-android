package dev.weazyexe.passkeys.utils

import android.util.Log
import com.google.gson.GsonBuilder
import dev.weazyexe.passkeys.app.error.ErrorResponse
import dev.weazyexe.passkeys.app.error.ResponseError
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.net.ConnectException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

/**
 * Flow builder on IO thread with error mapping
 */
fun <T> Flow<T>.io() = this
    .catch {
        Log.e("REKO", "Error", it)
        throw when (it) {
            is UnknownHostException,
            is ConnectException -> ResponseError.NoInternetError()
            is TimeoutException -> ResponseError.TimeoutError()

            is HttpException -> {
                val gson = GsonBuilder().create()
                val errorJson = it.response()?.errorBody()?.string() ?: throw ResponseError.UnknownError()
                val parsedError = gson.fromJson(errorJson, ErrorResponse::class.java)
                ResponseError.HttpError(arguments = arrayOf(parsedError.message))
            }

            else -> {
                ResponseError.UnknownError()
            }
        }
    }
    .flowOn(Dispatchers.IO)