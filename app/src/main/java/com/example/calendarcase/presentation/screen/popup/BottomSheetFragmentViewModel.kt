package com.example.calendarcase.presentation.screen.popup

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.usecase.DeleteNoteUseCase
import com.example.calendarcase.domain.usecase.GetNoteByDateUseCase
import com.example.calendarcase.domain.usecase.InsertNoteUseCase
import com.example.calendarcase.enum.RepositoryStatus
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class BottomSheetFragmentViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
): ViewModel(){

    fun insertNote(note: Note, success: (text: String) -> Unit ){
        insertNoteUseCase(note, viewModelScope){
            if(it.status == RepositoryStatus.OK){
                success("${it.data!!.title} has been inserted!")
            }
        }

    }

}