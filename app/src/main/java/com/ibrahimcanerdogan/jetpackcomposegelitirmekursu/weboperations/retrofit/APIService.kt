package com.ibrahimcanerdogan.jetpackcomposegelitirmekursu.weboperations.retrofit

import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("compose_webservice/all_person.php")
    suspend fun getAllPerson() : Response<PersonData>

    // if (isset($_POST['name']))
    // PHP dosyası içerisinde POST edilen değer @Field içerisinde verilmesi gerekiyor.
    @POST("compose_webservice/search_person.php")
    @FormUrlEncoded // Türkçe Dil Desteği
    suspend fun searchPerson(
        @Field("name") personName: String
    ) : Response<PersonData>

    // if (isset($_POST['id']))
    // PHP dosyası içerisinde POST edilen değer @Field içerisinde verilmesi gerekiyor.
    @POST("compose_webservice/delete_person.php")
    @FormUrlEncoded
    suspend fun deletePerson(
        @Field("id") personId: Int
    ) : Response<PersonMessage>

    // if (isset($_POST['name']) && isset($_POST['phone'])) {
    @POST("compose_webservice/insert_person.php")
    @FormUrlEncoded
    suspend fun addPerson(
        @Field("name") personName: String,
        @Field("phone") personPhone: String
    ) : Response<PersonMessage>

    // if (isset($_POST['id']) && isset($_POST['name']) && isset($_POST['phone']) ) {

    @POST("compose_webservice/update_person.php")
    @FormUrlEncoded
    suspend fun updatePerson(
        @Field("id") personId: Int,
        @Field("name") personName: String,
        @Field("phone") personPhone: String
    ) : Response<PersonMessage>

}