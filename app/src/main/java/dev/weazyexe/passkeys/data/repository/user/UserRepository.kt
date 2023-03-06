package dev.weazyexe.passkeys.data.repository.user

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.weazyexe.passkeys.app.error.ResponseError
import dev.weazyexe.passkeys.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    @ApplicationContext val context: Context
) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "user_repository")
    private val userKey = stringPreferencesKey("user")
    private val gson = Gson()

    suspend fun saveUser(user: User)  {
        context.dataStore.edit {
            it[userKey] = gson.toJson(user.asStorageEntity())
        }
    }

    fun getUser(): Flow<User> =
        context.dataStore.data
            .map { it[userKey] ?: throw ResponseError.UserNotFound() }
            .map { gson.fromJson(it, UserStorageEntity::class.java).asDomainEntity() }
}