package com.oguzhan.contactapp.di

import android.content.Context
import androidx.room.Room
import com.oguzhan.contactapp.data.local.ContactDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ContactDatabase = Room.databaseBuilder(
        context = context,
        klass = ContactDatabase::class.java,
        name = "contact_database"
    ).build()

    @Provides
    fun provideDao(database: ContactDatabase) = database.contactDao()

}