package com.fourgen.dogapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DogService @Inject constructor(
    private val api: DogApiClient
) {
    suspend fun getBreedImages(breed: String): List<String> {
        return withContext(Dispatchers.IO) {
            val response = api.getBreedImages(breed).body()
            if (response != null) {
                if (response?.status == "success") {
                    response?.message!!
                } else {
                    emptyList()
                }
            } else {
                emptyList()
            }

        }
    }

}