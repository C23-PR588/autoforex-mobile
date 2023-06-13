package com.c23pr588.autoforex

import android.media.session.MediaSession.Token
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c23pr588.autoforex.data.ApiConfig
import com.c23pr588.autoforex.data.CurrencyRepository
import com.c23pr588.autoforex.data.DummiesIDR
import com.c23pr588.autoforex.data.local.UserModel
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.data.traffic.CurrencyResponse
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem
//import com.c23pr588.autoforex.data.Currency
//import com.c23pr588.autoforex.data.CurrencyRepository
//import com.c23pr588.autoforex.data.CurrencyResponse
//import com.c23pr588.autoforex.settings.TokenPreference
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(private val pref: UserPreference, currencyRepository: CurrencyRepository): ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _listCurrency = MutableLiveData<List<ListCurrencyItem>>()
    val listCurrency: LiveData<List<ListCurrencyItem>> = _listCurrency

    fun getUser(): LiveData<UserModel> {
        return pref.getUser().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    fun getAllCurrencies() {
        _isLoading.value = true
        _listCurrency.value = DummiesIDR.listCurrencyData
        _isLoading.value = false
//        val token = runBlocking {
//            pref.fetchUser().first()
//        }
//        Log.d(TAG, token.toString())
//        val client = ApiConfig.getApiService().getCurrenciesData()
//        client.enqueue(object : Callback<CurrencyResponse> {
//            override fun onResponse(
//                call: Call<CurrencyResponse>,
//                response: Response<CurrencyResponse>
//            ) {
//                Log.d(TAG, "onResponse: ONSUCCESS BOS")
//                _isLoading.value = false
//                if (response.isSuccessful && response.body()?.error == false) {
//                    _listCurrency.value = response.body()?.listCurrency
//                    Log.d(TAG, "Request Successful")
//                } else {
//                    Log.d(TAG, response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
//                Log.d(TAG, "onFailure: ONFAILURE BOS")
//                _isLoading.value = false
//                Log.d(TAG, t.message.toString())
//            }
//        })
    }


}