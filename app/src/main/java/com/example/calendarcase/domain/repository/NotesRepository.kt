package com.example.calendarcase.domain.repository

import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.util.DataHolder

interface NotesRepository {
    suspend fun insertNote(note: Note): DataHolder<List<Note>>
    suspend fun deleteNote(note: Note): DataHolder<List<Note>>
    suspend fun getNoteByDate(date: String): DataHolder<List<Note>>
    suspend fun updateNote(note: Note): DataHolder<List<Note>>
}