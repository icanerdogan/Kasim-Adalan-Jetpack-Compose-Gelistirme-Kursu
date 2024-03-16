package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PersonData(
    @SerializedName("person")
    @Expose(deserialize = false, serialize = false)
    var personList: List<Person>,
    @SerializedName("success")
    @Expose
    var success: Int
)
