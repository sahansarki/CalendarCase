package com.example.calendarcase.domain.repository.mapper

import com.example.calendarcase.data.local.model.NoteEntity
import com.example.calendarcase.domain.model.Note

fun mapNoteToEntity(input: Note): NoteEntity{
    return NoteEntity(
        input.id,
        input.title,
        input.description,
        input.allDay,
        input.date,
        input.time,
        input.doesRepeat
    )
}