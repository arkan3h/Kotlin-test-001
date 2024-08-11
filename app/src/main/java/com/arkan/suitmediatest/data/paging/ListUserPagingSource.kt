package com.arkan.suitmediatest.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arkan.suitmediatest.data.datasource.UserDataSource
import com.arkan.suitmediatest.data.mapper.toUser
import com.arkan.suitmediatest.data.model.User
import okio.IOException
import retrofit2.HttpException

private const val USER_STARTING_PAGE_INDEX = 1

class ListUserPagingSource(
    private val dataSource: UserDataSource,
) : PagingSource<Int, User>() {
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        val pageIndex = params.key ?: USER_STARTING_PAGE_INDEX
        return try {
            val response = dataSource.getUser(page = pageIndex)
            val movies = response.data.toUser()
            val nextKey = if (movies.isEmpty()) null else pageIndex + 1
            LoadResult.Page(
                data = movies,
                prevKey = if (pageIndex == USER_STARTING_PAGE_INDEX) null else pageIndex,
                nextKey = nextKey,
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
