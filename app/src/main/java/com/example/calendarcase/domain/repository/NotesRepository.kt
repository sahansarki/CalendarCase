package com.example.calendarcase.domain.repository


import com.example.calendarcase.data.local.model.NoteEntity
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.util.DataHolder

interface NotesRepository {
    suspend fun insertNote(note: Note): DataHolder<Note>
    suspend fun deleteNote(note: Note): DataHolder<Note>
    suspend fun getNoteByDate(date: String): DataHolder<Note>
}