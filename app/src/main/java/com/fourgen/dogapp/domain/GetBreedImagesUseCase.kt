package com.fourgen.dogapp.domain

import com.fourgen.dogapp.data.network.DogService
import javax.inject.Inject

class GetBreedImagesUseCase @Inject constructor(
    private val api: DogService
) {
    suspend operator fun invoke(bread: String): List<String> {
        return api.getBreedImages(bread)
    }
}