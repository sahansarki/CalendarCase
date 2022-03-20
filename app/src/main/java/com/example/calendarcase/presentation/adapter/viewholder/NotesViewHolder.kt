package com.example.calendarcase.presentation.adapter.viewholder

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarcase.R
import com.example.calendarcase.databinding.ViewNoteItemBinding
import com.example.calendarcase.domain.model.Note

class NotesViewHolder(
    private val binding: ViewNoteItemBinding,
    private val onClickUpdate: (Note) -> Unit,
    private val onClickDelete: (Note) -> Unit,
    private val localContext: Context
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(note: Note) {
        binding.note = note
        binding.allDaySwitchButton.isChecked = note.allDay
        binding.replaySwitchButton.isChecked = note.doesRepeat
        binding.allDaySwitchButton.isClickable = false
        binding.replaySwitchButton.isClickable = false
        binding.noteCardMore.setOnClickListener {
            val popup = PopupMenu(localContext, it)
            popup.menuInflater.inflate(R.menu.note_item_menu, popup.menu)
            popup.show()

            popup.setOnMenuItemClickListener { item ->
                when (item?.itemId) {
                    R.id.action_delete -> {
                        onClickDelete(note)
                        true
                    }

                    R.id.action_update -> {
                        onClickUpdate(note)
                        true
                    }

                    else -> false
                }
            }
        }
    }

    companion object {
        fun create(
            parent: ViewGroup,
            onClickUpdate: (Note) -> Unit,
            onClickDelete: (Note) -> Unit,
        ): NotesViewHolder {
            val view = ViewNoteItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return NotesViewHolder(view, onClickUpdate, onClickDelete, parent.context)
        }
    }
}
