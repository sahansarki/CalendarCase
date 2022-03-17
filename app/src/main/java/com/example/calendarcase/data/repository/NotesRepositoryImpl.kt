package com.example.calendarcase.data.repository

import com.example.calendarcase.data.local.NotesDao
import com.example.calendarcase.data.local.model.NoteEntity
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.repository.NotesRepository
import com.example.calendarcase.domain.repository.mapper.mapNoteEntity
import com.example.calendarcase.domain.repository.mapper.mapNoteToEntity
import com.example.calendarcase.util.CalendarError
import com.example.calendarcase.util.DataHolder
import java.lang.Exception
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private val notesDao: NotesDao
): NotesRepository {
    override suspend fun insertNote(note: Note): DataHolder<Note> {

        val result: DataHolder<Note> = try{
            val data = mapNoteToEntity(note)
            notesDao.insertNote(data)

            DataHolder.success(note)
        } catch (e: Exception){
            DataHolder.error(CalendarError(e.localizedMessage,e) ,note)
        }

        return result

    }

    override suspend fun deleteNote(note: Note): DataHolder<Note> {

        val result: DataHolder<Note> = try{
            val data = mapNoteToEntity(note)
            notesDao.deleteNote(data)

            DataHolder.success(note)
        } catch (e: Exception){
            DataHolder.error(CalendarError(e.localizedMessage, e),note)
        }

        return  result
    }

    override suspend fun getNoteByDate(date: String): DataHolder<Note> {

        val result: DataHolder<Note> = try{
            val noteEntity = notesDao.getNoteByDate(date)
            val dataNote = mapNoteEntity(noteEntity)
            DataHolder.success(dataNote)
        } catch (e: Exception){
            DataHolder.error(CalendarError(e.localizedMessage, e),null)
        }

        return result
    }
}