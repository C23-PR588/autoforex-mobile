package com.c23pr588.autoforex.data.traffic

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(
    @field:SerializedName("status")
    val status: Int,
)