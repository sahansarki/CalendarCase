package com.example.calendarcase.util

class CalendarError(
    override val message: String,
    override val cause: Throwable? = null
) : Throwable(null, null)