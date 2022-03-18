package com.example.calendarcase.data.repository

import com.example.calendarcase.data.local.NotesDao
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.repository.NotesRepository
import com.example.calendarcase.domain.repository.mapper.mapNoteEntity
import com.example.calendarcase.domain.repository.mapper.mapNoteToEntity
import com.example.calendarcase.util.CalendarError
import com.example.calendarcase.util.DataHolder
import javax.inject.Inject
import kotlin.Exception

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
) : NotesRepository {
    override suspend fun insertNote(note: Note): DataHolder<List<Note>> {

        val result: DataHolder<List<Note>> = try {
            val data = mapNoteToEntity(note)
            notesDao.insertNote(data)
            DataHolder.success(listOf(note))
        } catch (e: Exception) {
            DataHolder.error(CalendarError(e.localizedMessage, e), listOf(note))
        }

        return result

    }

    override suspend fun deleteNote(note: Note): DataHolder<List<Note>> {

        val result: DataHolder<List<Note>> = try {
            val data = mapNoteToEntity(note)
            notesDao.deleteNote(data)

            DataHolder.success(listOf(note))
        } catch (e: Exception) {
            DataHolder.error(CalendarError(e.localizedMessage, e), listOf(note))
        }

        return result
    }

    override suspend fun getNoteByDate(date: String): DataHolder<List<Note>> {

        val result: DataHolder<List<Note>> = try {
            val noteEntityList = notesDao.getNoteByDate(date)
            val noteList = arrayListOf<Note>()

            noteEntityList.forEach {
                val dataNote = mapNoteEntity(it)
                noteList.add(dataNote)
            }

            DataHolder.success(noteList)
        } catch (e: Exception) {
            DataHolder.error(CalendarError("No Data found on that date", e), null)
        }

        return result
    }

    override suspend fun updateNote(note: Note): DataHolder<List<Note>> {

        val result: DataHolder<List<Note>> = try {
            val data = mapNoteToEntity(note)
            notesDao.updateNote(data)
            DataHolder.success(listOf(note))
        } catch (e: Exception) {
            DataHolder.error(CalendarError(e.localizedMessage, e), listOf(note))
        }

        return result
    }
}