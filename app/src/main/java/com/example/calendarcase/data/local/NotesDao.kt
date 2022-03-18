package com.example.calendarcase.data.local

import androidx.room.*
import com.example.calendarcase.data.local.model.NoteEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM notes_table WHERE date = :date")
    suspend fun getNoteByDate(date: String): List<NoteEntity>

    @Update
    suspend fun updateNote(note: NoteEntity)
}