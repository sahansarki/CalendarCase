package com.example.calendarcase.di

import com.example.calendarcase.data.repository.NotesRepositoryImpl
import com.example.calendarcase.domain.repository.NotesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {
    @Binds
    @NotesRepositoryImp
    fun bindQuotesRepository(quotesRepositoryImpl: NotesRepositoryImpl): NotesRepository

}

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NotesRepositoryImp


