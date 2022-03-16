package com.example.calendarcase.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Note(
    val id: Int = 0,
    val title: String,
    val description: String,
    val allDay: Boolean,
    val date: String,
    val time: String,
    val doesRepeat: Boolean
): Parcelable
