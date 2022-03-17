package com.example.calendarcase.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "NOTES_TABLE")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val title: String,
    val description: String,
    val allDay: Boolean,
    val date: String,
    val time: String,
    val doesRepeat: Boolean
)
