package com.android.api_service.paging

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.android.api_service.service.DiscoverService
import com.android.entity.discover.DiscoverMovie

class DiscoverDataSource(
    private val discoverService: DiscoverService,
    private val genres: String
) : PagingSource<Int, DiscoverMovie>() {
    override fun getRefreshKey(state: PagingState<Int, DiscoverMovie>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, DiscoverMovie> {
        val page = params.key ?: 1
        val prevPage = if (page == 1) null else page - 1

        try {
            val data = discoverService.getMovieByGenre(genres, page)

            if (data.isSuccessful){
                data.body()?.let {
                    val nextPage = if (it.discoverMovies.isEmpty()) null else page + 1

                    return LoadResult.Page(it.discoverMovies, prevPage, nextPage)
                }?: kotlin.run {
                    return LoadResult.Page(arrayListOf(), prevPage, null)
                }
            } else {
                return LoadResult.Error(Exception("Error Backend: ${data.code()}"))
            }
        }catch (e: Exception){
            return LoadResult.Error(e)
        }
    }
}

object DiscoverMoviePager{
    fun createPager(
        pageSize: Int,
        discoverService: DiscoverService,
        genres: String
    ): Pager<Int, DiscoverMovie> = Pager(
        config = PagingConfig(pageSize),
        pagingSourceFactory = {
            DiscoverDataSource(discoverService, genres)
        }
    )
}