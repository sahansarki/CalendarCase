package com.example.calendarcase.domain.usecase

import com.example.calendarcase.di.NotesRepositoryImp
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.repository.NotesRepository
import com.example.calendarcase.util.DataHolder
import javax.inject.Inject

class InsertNoteUseCase @Inject constructor(
    @NotesRepositoryImp private val notesRepository: NotesRepository
) : UseCase<Note>() {
    override suspend fun run(params: Note): DataHolder<List<Note>> {
        return notesRepository.insertNote(params)
    }

}