package dev.weazyexe.passkeys.domain

import java.util.*

data class Credential(
    val id: String,
    val deviceName: String,
    val lastOnline: Date
) {

    companion object {

        fun mock(): Credential = Credential(
            id = UUID.randomUUID().toString(),
            deviceName = "Pixel 6",
            lastOnline = Date()
        )
    }
}