package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.weboperations.retrofit

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import retrofit2.Call
import java.io.IOException

class CallTypeAdapter : TypeAdapter<Call<*>>() {
    @Throws(IOException::class)
    override fun write(out: JsonWriter, value: Call<*>?) {
        throw UnsupportedOperationException("Serialization of retrofit2.Call is not supported")
    }

    @Throws(IOException::class)
    override fun read(`in`: JsonReader): Call<*>? {
        throw UnsupportedOperationException("Deserialization of retrofit2.Call is not supported")
    }
}