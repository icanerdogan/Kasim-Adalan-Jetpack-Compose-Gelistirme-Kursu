package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.compose7weboperations.firebase

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FirebasePerson(
    var personName: String? = null,
    var personAge: Int? = null
)