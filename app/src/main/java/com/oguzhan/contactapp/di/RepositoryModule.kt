package com.oguzhan.contactapp.di

import com.oguzhan.contactapp.data.repository.ContactDaoRepositoryImpl
import com.oguzhan.contactapp.data.repository.ContactRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindContactRepository(
        contactDaoRepositoryImpl: ContactDaoRepositoryImpl
    ): ContactRepository
}