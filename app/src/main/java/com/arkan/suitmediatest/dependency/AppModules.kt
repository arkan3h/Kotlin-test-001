package com.arkan.suitmediatest.dependency

import com.arkan.suitmediatest.data.datasource.UserApiDataSource
import com.arkan.suitmediatest.data.datasource.UserDataSource
import com.arkan.suitmediatest.data.repository.UserRepository
import com.arkan.suitmediatest.data.repository.UserRepositoryImpl
import com.arkan.suitmediatest.data.source.network.services.SuitMediaApiServices
import com.arkan.suitmediatest.presentation.firstpage.MainViewModel
import com.arkan.suitmediatest.presentation.secondpage.SecondViewModel
import com.arkan.suitmediatest.presentation.thirdpage.ThirdViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

object AppModules {
    private val networkModule = module { single<SuitMediaApiServices> { SuitMediaApiServices.invoke() } }

    private val dataSource = module { single<UserDataSource> { UserApiDataSource(get()) } }

    private val repository = module { single<UserRepository> { UserRepositoryImpl(get()) } }

    private val viewModelModule =
        module {
            viewModelOf(::MainViewModel)
            viewModelOf(::SecondViewModel)
            viewModelOf(::ThirdViewModel)
        }

    val modules =
        listOf(
            networkModule,
            dataSource,
            repository,
            viewModelModule,
        )
}
