package com.android.api_service.usecase

import com.android.api_service.service.GenreService
import com.android.common.AppResponse
import com.android.entity.genre.Genre
import com.android.usecase.BaseUseCase
import kotlinx.coroutines.flow.flow

class GenreUsecase(private val genreService: GenreService): BaseUseCase() {
    operator fun invoke() = flow<AppResponse<List<Genre>>>{

        try {
//            delay(5000)
            emit(AppResponse.loading())
            val data = genreService.getGenreData()
            if (data.isSuccessful){
                data.body()?.let {
                    emit(AppResponse.success(it.genres))
                }?: run{
                    emit(AppResponse.errorBackend(data.code(), data.errorBody()))
                }
            }else{
                emit(AppResponse.errorBackend(data.code(), data.errorBody()))
            }
        }catch (e: Exception){
            emit(AppResponse.errorSystem(e))
        }
    }
}