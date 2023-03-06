package dev.weazyexe.passkeys.data.network.auth

import dev.weazyexe.passkeys.data.network.auth.dto.UserRegisterRequest
import dev.weazyexe.passkeys.data.network.auth.dto.UserRegisterResponse
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("/auth/register/request")
    suspend fun registerRequest(@Body request: UserRegisterRequest): Response<ResponseBody>

    @POST("/auth/register/response")
    suspend fun registerResponse(@Body response: RequestBody): UserRegisterResponse
}