package com.example.calendarcase.data.repository

import com.example.calendarcase.data.local.NotesDao
import com.example.calendarcase.data.local.model.NoteEntity
import com.example.calendarcase.domain.repository.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override suspend fun insertNote(note: NoteEntity) {
        notesDao.insertNote(note)
    }

    override suspend fun deleteNote(note: NoteEntity) {
        notesDao.deleteNote(note)
    }

    override suspend fun getNoteByDate(date: String): NoteEntity {
        return notesDao.getNoteByDate(date)
    }
}