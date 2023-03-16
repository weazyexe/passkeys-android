package dev.weazyexe.passkeys.data.repository.auth

import androidx.credentials.CreatePublicKeyCredentialRequest
import dev.weazyexe.passkeys.data.network.auth.AuthApi
import dev.weazyexe.passkeys.data.network.auth.dto.UserRegisterRequest
import dev.weazyexe.passkeys.data.network.auth.dto.asDomainEntity
import dev.weazyexe.passkeys.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.HttpException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthRepository @Inject constructor(
    private val authApi: AuthApi
) {

    fun startRegistration(username: String): Flow<CreatePublicKeyCredentialRequest> = flow {
        val response = authApi.registerRequest(UserRegisterRequest(username))
        if (!response.isSuccessful) {
            throw HttpException(response)
        }

        val body = response.body()?.string() ?: throw Exception("Response body is empty")
        emit(CreatePublicKeyCredentialRequest(body))
    }

    fun sendRegistrationResponse(response: String): Flow<User> = flow {
        val body = response.toRequestBody("application/json".toMediaType())
        emit(authApi.registerResponse(body).asDomainEntity())
    }
}