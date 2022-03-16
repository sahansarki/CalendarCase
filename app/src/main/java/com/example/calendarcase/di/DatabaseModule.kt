package com.example.calendarcase.di

import android.app.Application
import com.example.calendarcase.data.local.NotesDao
import com.example.calendarcase.data.local.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideNotesDatabase(application: Application): NotesDatabase =
        NotesDatabase.getInstance(application)

    @Singleton
    @Provides
    fun provideNotesDao(database: NotesDatabase): NotesDao = database.notesDao()

}
