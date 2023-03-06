package dev.weazyexe.passkeys.data.repository.user

import com.google.gson.annotations.SerializedName
import dev.weazyexe.passkeys.domain.User

data class UserStorageEntity(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String
)

fun User.asStorageEntity(): UserStorageEntity = UserStorageEntity(id, name)

fun UserStorageEntity.asDomainEntity(): User = User(id, name)