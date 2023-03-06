package dev.weazyexe.passkeys.app.error

import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("message") val message: String
)