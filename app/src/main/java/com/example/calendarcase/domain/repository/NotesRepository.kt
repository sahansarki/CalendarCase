package com.example.calendarcase.domain.repository


import com.example.calendarcase.data.local.model.NoteEntity

interface NotesRepository {
    suspend fun insertNote(note: NoteEntity)
    suspend fun deleteNote(note: NoteEntity)
    suspend fun getNoteByDate(date: String): NoteEntity
}