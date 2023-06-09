package com.c23pr588.autoforex.data.traffic

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class CurrencyResponse(
    @field:SerializedName("error")
    val error: Boolean,

    @field:SerializedName("message")
    val message: String,

    @field:SerializedName("listCurrency")
    val listCurrency: List<ListCurrencyItem>,
)

@Parcelize
data class ListCurrencyItem(

)