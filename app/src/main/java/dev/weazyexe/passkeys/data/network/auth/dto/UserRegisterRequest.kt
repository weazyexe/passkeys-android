package dev.weazyexe.passkeys.data.network.auth.dto

import com.google.gson.annotations.SerializedName

data class UserRegisterRequest(
    @SerializedName("username") val username: String
)