package com.c23pr588.autoforex.data

import android.content.Context

object Injection {
    fun provideRepository(context: Context): CurrencyRepository {
        val apiService = ApiConfig.getApiService()
        return CurrencyRepository(apiService, context)
    }
}