package com.android.themoviedbapp.view_model

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.android.api_service.usecase.DiscoverPagingUsecase
import com.android.common.BaseViewModel
import com.android.entity.discover.DiscoverMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscoverViewModel @Inject constructor(
    application: Application,
    val discoverPagingUsecase: DiscoverPagingUsecase
): BaseViewModel(application) {

    val discoverDataPaging = MutableLiveData<PagingData<DiscoverMovie>>()

    fun getDiscover(genreId: String){

        viewModelScope.launch {
            delay(1000)
            discoverPagingUsecase.invoke(genreId).collect{
                discoverDataPaging.postValue(it)
            }
        }
    }
}