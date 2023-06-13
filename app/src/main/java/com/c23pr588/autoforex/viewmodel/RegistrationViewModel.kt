package com.c23pr588.autoforex.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.c23pr588.autoforex.data.ApiConfig
import com.c23pr588.autoforex.data.Registration
import com.c23pr588.autoforex.data.local.UserModel
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.data.traffic.RegistrationResponse
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegistrationViewModel(private val pref: UserPreference) : ViewModel() {
    fun saveUser(user: UserModel) {
        _isLoading.value = true
        viewModelScope.launch {
            pref.saveUser(user)
        }
        _isLoading.value = false
    }

//    private val _registrationDetails = MutableLiveData<RegistrationResponse>()
//    val registrationDetails: LiveData<RegistrationResponse> = _registrationDetails
//
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading
//
//    companion object {
//        private const val TAG = "Registration Req"
//    }
//
//    fun getRegistrationDetails(firstName: String, lastName: String, email: String, password: String) {
//        _isLoading.value = true
//        val body = Registration(firstName, lastName, email, password)
//        val client = ApiConfig.getApiService().registration(body)
//        client.enqueue(object: Callback<RegistrationResponse> {
//            override fun onResponse(
//                call: Call<RegistrationResponse>,
//                response: Response<RegistrationResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _registrationDetails.value = response.body()
//                    Log.e(TAG, "onSuccess: ${response.body()}")
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//
//            override fun onFailure(call: Call<RegistrationResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })
//    }
}