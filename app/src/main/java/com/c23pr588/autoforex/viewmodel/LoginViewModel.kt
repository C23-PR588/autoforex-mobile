package com.c23pr588.autoforex.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.c23pr588.autoforex.data.ApiConfig
import com.c23pr588.autoforex.data.Login
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.data.traffic.LoginResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel (private val pref: UserPreference): ViewModel() {
    private val _loginDetails = MutableLiveData<LoginResponse>()
    val loginDetails: LiveData<LoginResponse> = _loginDetails

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    companion object {
        private const val TAG = "Login Req"
    }

    fun getLoginDetails(username: String, password: String) {
        _isLoading.value = true
        val body = Login(username, password)
        val client = ApiConfig.getApiService().login(body)
        client.enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                _isLoading.value = false
                Log.d(TAG, "INI DI ONSUC")
                if (response.isSuccessful) {
                    _loginDetails.value = response.body()
                    Log.e(TAG, "onSuccess: ${response.body()}")
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, "INI DI ONFAIL")
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun fetchUser(): LiveData<Boolean> {
        return pref.fetchUser().asLiveData()
    }

    fun saveUsername(username: String) {
        viewModelScope.launch {
            pref.saveUsername(username)
        }
    }

    fun savePassword(password: String) {
        viewModelScope.launch {
            pref.savePassword(password)
        }
    }

    fun saveLogin() {
        viewModelScope.launch {
            pref.saveLogin()
        }
    }
}