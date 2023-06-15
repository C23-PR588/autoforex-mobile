package com.c23pr588.autoforex.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.c23pr588.autoforex.data.local.UserPreference
import com.c23pr588.autoforex.data.traffic.ListCurrencyItem

class PurchaseViewModel(private val pref: UserPreference) : ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _purchaseValue = MutableLiveData<Int>()
    val purchaseValue: LiveData<Int> = _purchaseValue

    fun purchaseCurrency(currency: Int) {
        _isLoading.value = true
        _purchaseValue.value = currency
        _isLoading.value = false
    }
}