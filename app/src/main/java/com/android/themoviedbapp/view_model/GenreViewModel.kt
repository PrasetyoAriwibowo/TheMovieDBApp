package com.android.themoviedbapp.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.android.api_service.usecase.GenreUsecase
import com.android.common.AppResponse
import com.android.common.BaseViewModel
import com.android.entity.genre.Genre
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GenreViewModel @Inject constructor(
    application: Application,
    val genreUsecase: GenreUsecase
) : BaseViewModel(application) {

    val dataGenre = MutableLiveData<AppResponse<List<Genre>>>()
    val selection = MutableLiveData<List<Genre>>()

    fun getGenre() {
        viewModelScope.launch {
            delay(1000)
            genreUsecase().collect {
                dataGenre.postValue(it)
            }
        }
    }

    init {
        getGenre()
    }
}