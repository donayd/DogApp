package com.fourgen.dogapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fourgen.dogapp.domain.GetBreedImagesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogViewModel @Inject constructor(
    private val getBreedsUseCase: GetBreedImagesUseCase
) : ViewModel() {

    val dogsBreeds = MutableLiveData<List<String>>()
    val isLoading = MutableLiveData<Boolean>()
    val isFail = MutableLiveData<Boolean>()

    fun onCreate(bread: String) {
        viewModelScope.launch {
            isLoading.postValue(true)

            val dogs = getBreedsUseCase(bread)

            if (!dogs.isNullOrEmpty()) {
                dogsBreeds.postValue(dogs)
                isLoading.postValue(false)
                isFail.postValue(false)
            } else {
                isFail.postValue(true)
            }
        }
    }
}