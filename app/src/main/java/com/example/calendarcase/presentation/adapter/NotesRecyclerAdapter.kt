package com.example.calendarcase.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.presentation.adapter.viewholder.NotesViewHolder


class NotesRecyclerAdapter(
    private val onClickUpdate: (Note) -> Unit,
    private val onClickDelete: (Note) -> Unit,
) : RecyclerView.Adapter<NotesViewHolder>() {
    val differ = AsyncListDiffer(this, DIFF_CALLBACK)

    override fun getItemCount() = differ.currentList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder.create(
            parent,
            onClickUpdate,
            onClickDelete
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Note>() {
            override fun areItemsTheSame(oldItem: Note, newItem: Note) = oldItem == newItem
            override fun areContentsTheSame(oldItem: Note, newItem: Note) =
                oldItem.id == newItem.id
        }
    }
}
