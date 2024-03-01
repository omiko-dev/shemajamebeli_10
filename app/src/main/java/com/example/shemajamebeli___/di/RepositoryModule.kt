package com.example.shemajamebeli___.di

import com.example.shemajamebeli___.data.common.HandleResource
import com.example.shemajamebeli___.data.repository.AccountRepositoryImpl
import com.example.shemajamebeli___.data.service.AccountService
import com.example.shemajamebeli___.domain.repository.AccountRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideAccountRepository(accountService: AccountService, handleResource: HandleResource): AccountRepository =
        AccountRepositoryImpl(accountService = accountService, handleResource = handleResource)
}