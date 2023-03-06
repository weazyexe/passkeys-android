package dev.weazyexe.passkeys.domain

import java.util.*

data class User(
    val id: String,
    val name: String
) {
    companion object {

        fun mock(): User = User(id = UUID.randomUUID().toString(), name = "weazyexe")
    }
}