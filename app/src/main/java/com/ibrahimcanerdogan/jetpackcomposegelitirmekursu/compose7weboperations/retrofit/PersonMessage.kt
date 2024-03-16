package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonMessage(
    @SerializedName("success")
    @Expose
    var success: Int,
    @SerializedName("message")
    @Expose
    var message: String
)
