package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Person(
    @SerializedName("id")
    @Expose
    var personID: Int,
    @SerializedName("name")
    @Expose
    var personName: String,
    @SerializedName("phone")
    @Expose
    var personPhone: String
)