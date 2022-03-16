package com.example.calendarcase.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.calendarcase.data.local.model.NoteEntity

@Database(entities = [NoteEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao

    companion object {
        private const val DB_NAME = "notes_db"

        @Volatile
        private var INSTANCE: NotesDatabase? = null

        fun getInstance(context: Context): NotesDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    DB_NAME
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}