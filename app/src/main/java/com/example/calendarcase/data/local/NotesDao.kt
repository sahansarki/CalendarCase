package com.example.calendarcase.data.local

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.calendarcase.data.local.model.NoteEntity

interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes_table WHERE date = :date")
    fun getNoteByDate(date: String): NoteEntity
}