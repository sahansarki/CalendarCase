package com.example.calendarcase.data.repository.mapper

import com.example.calendarcase.data.local.model.NoteEntity
import com.example.calendarcase.domain.model.Note

fun mapNoteEntity(input: NoteEntity): Note {
    return Note(
        input.id,
        input.title,
        input.description,
        input.allDay,
        input.date,
        input.time,
        input.doesRepeat,
    )
}