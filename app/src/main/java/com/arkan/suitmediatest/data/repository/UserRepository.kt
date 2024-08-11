package com.arkan.suitmediatest.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.arkan.suitmediatest.data.datasource.UserDataSource
import com.arkan.suitmediatest.data.model.User
import com.arkan.suitmediatest.data.paging.ListUserPagingSource
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    fun getUser(): Flow<PagingData<User>>
}

class UserRepositoryImpl(
    private val dataSource: UserDataSource,
) : UserRepository {
    override fun getUser(): Flow<PagingData<User>> =
        Pager(
            pagingSourceFactory = { ListUserPagingSource(dataSource) },
            config = PagingConfig(pageSize = 10),
        ).flow
}
