package dev.weazyexe.passkeys.data.network.auth.dto

import com.google.gson.annotations.SerializedName
import dev.weazyexe.passkeys.domain.User

data class UserRegisterResponse(
    @SerializedName("id") val id: String,
    @SerializedName("username") val name: String
)

fun UserRegisterResponse.asDomainEntity() = User(id, name)