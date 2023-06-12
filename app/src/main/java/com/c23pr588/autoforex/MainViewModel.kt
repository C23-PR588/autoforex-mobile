package com.c23pr588.autoforex

import android.media.session.MediaSession.Token
import android.util.Log
import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.c23pr588.autoforex.data.ApiConfig
import com.c23pr588.autoforex.data.local.UserPreference
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

class MainViewModel(private val pref: UserPreference, storyRepository: StoryRepository): ViewModel() {

    companion object {
        const val TAG = "MainViewModel"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    val currency: LiveData<PagingData<ListCurrencyItem>> =
        storyRepository.getStory().cachedIn(viewModelScope)

    private val _listStory = MutableLiveData<List<Story>>()
    val listStory: LiveData<List<Story>> = _listStory

    fun getToken(): LiveData<String> {
        return pref.getToken().asLiveData()
    }

    fun logout() {
        viewModelScope.launch {
            pref.logout()
        }
    }

    fun getAllStory() {
        _isLoading.value = true
        val token = runBlocking {
            pref.getToken().first()
        }
        Log.d(TAG, token)
        val client = APIConfig.getAPIService().getStory("Bearer $token")
        client.enqueue(object : Callback<StoryResponse> {
            override fun onResponse(
                call: Call<StoryResponse>,
                response: Response<StoryResponse>
            ) {
                _isLoading.value = false
                if (response.isSuccessful && response.body()?.error == false) {
                    _listStory.value = response.body()?.listStory
                    Log.d(TAG, "Request Successful")
                } else {
                    Log.d(TAG, response.message())
                }
            }

            override fun onFailure(call: Call<StoryResponse>, t: Throwable) {
                _isLoading.value = false
                Log.d(TAG, t.message.toString())
            }
        })
    }
}