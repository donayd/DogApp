package com.fourgen.dogapp.data.network

import com.fourgen.dogapp.data.module.DogModule
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DogApiClient {

    @GET("list/all")
    suspend fun getAllBreeds(): Response<DogModule>

    @GET("{breed}/images")
    suspend fun getBreedImages(@Path("breed") breed: String): Response<DogModule>

}