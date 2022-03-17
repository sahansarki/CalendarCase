package com.example.calendarcase.presentation.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.usecase.GetNoteByDateUseCase
import com.example.calendarcase.util.DataHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val getNoteByDateUseCase: GetNoteByDateUseCase
): ViewModel() {

    private val mutableNote =  MutableLiveData<DataHolder<Note>>()
    val note: LiveData<DataHolder<Note>>
        get() = mutableNote

    fun getNoteByDate(date: String){
        getNoteByDateUseCase(date, viewModelScope){
            mutableNote.value = it
        }
    }

}