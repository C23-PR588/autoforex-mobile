package com.c23pr588.autoforex.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreference(private val dataStore: DataStore<Preferences>) {

    suspend fun saveUsername(username: String){
        dataStore.edit { preferences ->
            preferences[USERNAME] = username
        }
    }

    suspend fun savePassword(password: String){
        dataStore.edit { preferences ->
            preferences[PASSWORD] = password
        }
    }

    suspend fun saveLogin(){
        dataStore.edit { preferences ->
            preferences[IS_LOGIN] = true
        }
    }

    fun fetchUser(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[IS_LOGIN] ?: false
        }
    }

    suspend fun deleteUser(){
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreference? = null
        private val USERNAME = stringPreferencesKey("Username")
        private val PASSWORD = stringPreferencesKey("Password")
        private val IS_LOGIN = booleanPreferencesKey("Logged In")

        fun getInstance(dataStore: DataStore<Preferences>): UserPreference{
            return INSTANCE ?: synchronized(this){
                UserPreference(dataStore).also {
                    INSTANCE = it
                }
            }
        }
    }
}
