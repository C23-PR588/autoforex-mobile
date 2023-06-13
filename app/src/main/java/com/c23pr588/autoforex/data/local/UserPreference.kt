package com.c23pr588.autoforex.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference private constructor(private val dataStore: DataStore<Preferences>) {
    fun getUser(): Flow<UserModel> {
        return dataStore.data.map {preferences ->
            UserModel(
                preferences[FIRSTNAME_KEY] ?:"",
                preferences[LASTNAME_KEY] ?:"",
                preferences[USERNAME_KEY] ?:"",
                preferences[PASSWORD_KEY] ?:"",
                preferences[STATE_KEY] ?: false
            )
        }
    }

    fun getFirstName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[FIRSTNAME_KEY] ?: ""
        }
    }

    suspend fun saveUser(user: UserModel) {
        dataStore.edit { preferences ->
            preferences[FIRSTNAME_KEY] = user.firstName
            preferences[LASTNAME_KEY] = user.lastName
            preferences[USERNAME_KEY] = user.username
            preferences[PASSWORD_KEY] = user.password
            preferences[STATE_KEY] = user.isLogin
        }
    }

    suspend fun login() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = true
        }
    }

    suspend fun logout() {
        dataStore.edit { preferences ->
            preferences[STATE_KEY] = false
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null

        private val FIRSTNAME_KEY = stringPreferencesKey("firstname")
        private val LASTNAME_KEY = stringPreferencesKey("lastname")
        private val USERNAME_KEY = stringPreferencesKey("username")
        private val PASSWORD_KEY = stringPreferencesKey("password")
        private val STATE_KEY = booleanPreferencesKey("state")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}

//class UserPreference(private val dataStore: DataStore<Preferences>) {
//
//    suspend fun saveUsername(username: String){
//        dataStore.edit { preferences ->
//            preferences[USERNAME] = username
//        }
//    }
//
//    suspend fun savePassword(password: String){
//        dataStore.edit { preferences ->
//            preferences[PASSWORD] = password
//        }
//    }
//
//    suspend fun saveLogin(){
//        dataStore.edit { preferences ->
//            preferences[IS_LOGIN] = true
//        }
//    }
//
//    fun fetchUser(): Flow<Boolean> {
//        return dataStore.data.map { preferences ->
//            preferences[IS_LOGIN] ?: false
//        }
//    }
//
//    suspend fun deleteUser(){
//        dataStore.edit { preferences ->
//            preferences.clear()
//        }
//    }
//
//    companion object {
//        @Volatile
//        private var INSTANCE: UserPreference? = null
//        private val USERNAME = stringPreferencesKey("Username")
//        private val PASSWORD = stringPreferencesKey("Password")
//        private val IS_LOGIN = booleanPreferencesKey("Logged In")
//
//        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
//            return INSTANCE ?: synchronized(this){
//                UserPreference(dataStore).also {
//                    INSTANCE = it
//                }
//            }
//        }
//    }
//}
