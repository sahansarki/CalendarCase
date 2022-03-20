package com.example.calendarcase.presentation.screen.popup

import androidx.lifecycle.viewModelScope
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.domain.usecase.InsertNoteUseCase
import com.example.calendarcase.domain.usecase.UpdateNoteUseCase
import com.example.calendarcase.enum.RepositoryStatus
import com.example.calendarcase.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import javax.inject.Inject

@DelicateCoroutinesApi
@HiltViewModel
class BottomSheetFragmentViewModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
) : BaseViewModel() {

    fun insertNote(note: Note, success: (text: String) -> Unit) {

        job?.cancel()

        insertNoteUseCase(note, viewModelScope) {
            if (it.status == RepositoryStatus.OK) {
                success("${it.data!![0].title} has been inserted!")
            }
        }
    }

    fun updateNote(note: Note, success: (text: String) -> Unit) {
        job?.cancel()

        updateNoteUseCase(note, viewModelScope) {
            if (it.status == RepositoryStatus.OK) {
                success("${it.data!![0].title} has been updated.")
            } else if (it.status == RepositoryStatus.ERROR) {
                println("Error")
            }
        }
    }

}