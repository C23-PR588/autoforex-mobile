package com.c23pr588.autoforex.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CurrencyAttributes(
    val name: String?,
    val currentValue: Double?,
): Parcelable
