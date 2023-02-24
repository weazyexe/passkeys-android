package dev.weazyexe.passkeys.data.network.auth.dto

import com.google.gson.annotations.SerializedName

data class UserRegisterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("username") val name: String
)