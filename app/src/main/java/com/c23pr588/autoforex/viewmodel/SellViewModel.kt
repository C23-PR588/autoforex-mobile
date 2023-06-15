package com.c23pr588.autoforex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.c23pr588.autoforex.data.local.UserPreference

class SellViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _sellValue = MutableLiveData<Int>()
    val sellValue: LiveData<Int> = _sellValue

    fun sellCurrency(currency: Int) {
        _isLoading.value = true
        _sellValue.value = currency
        _isLoading.value = false
    }
}