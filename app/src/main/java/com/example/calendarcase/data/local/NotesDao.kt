package com.example.calendarcase.data.local

import androidx.room.*
import com.example.calendarcase.data.local.model.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertNote(note: NoteEntity)

    @Delete
    fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes_table WHERE date = :date")
    fun getNoteByDate(date: String): NoteEntity
}