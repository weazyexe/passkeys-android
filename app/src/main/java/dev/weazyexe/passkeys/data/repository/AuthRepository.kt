package dev.weazyexe.passkeys.data.repository

import androidx.credentials.CreatePublicKeyCredentialRequest
import dev.weazyexe.passkeys.data.network.auth.AuthApi
import dev.weazyexe.passkeys.data.network.auth.dto.UserRegisterRequest
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    fun signIn(username: String): Flow<CreatePublicKeyCredentialRequest> = flow {
        val response = authApi.registerRequest(UserRegisterRequest(username))
        if (!response.isSuccessful) {
            throw Exception("Response code isn't 2xx")
        }

        val body = response.body()?.string() ?: throw Exception("Response body is empty")
        emit(CreatePublicKeyCredentialRequest(body))
    }
}