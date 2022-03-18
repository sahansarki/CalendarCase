package com.example.calendarcase.presentation.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.usecase.DeleteNoteUseCase
import com.example.calendarcase.domain.usecase.GetNoteByDateUseCase
import com.example.calendarcase.domain.usecase.UpdateNoteUseCase
import com.example.calendarcase.enum.RepositoryStatus
import com.example.calendarcase.presentation.base.BaseViewModel
import com.example.calendarcase.util.DataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getNoteByDateUseCase: GetNoteByDateUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
): BaseViewModel() {

    private val mutableNote =  MutableLiveData<DataHolder<List<Note>>>()
    val note: LiveData<DataHolder<List<Note>>>
        get() = mutableNote

    fun getNoteByDate(date: String){
        job?.cancel()

        getNoteByDateUseCase(date, viewModelScope){
            mutableNote.value = it
        }
    }

    fun deleteNote(note: Note, success: (text: String) -> Unit){
        job?.cancel()

        deleteNoteUseCase(note, viewModelScope){
            if(it.status == RepositoryStatus.OK){
                success("${it.data!![0].title} has been deleted.")
            }
        }
    }

}