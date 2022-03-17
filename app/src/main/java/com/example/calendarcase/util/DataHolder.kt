package com.example.calendarcase.util

import com.example.calendarcase.enum.RepositoryStatus

class DataHolder<out T> private constructor(val status: RepositoryStatus, val data: T?, val error: CalendarError?) {

    companion object {

        fun <T> success(data: T): DataHolder<T> {
            return DataHolder(RepositoryStatus.OK, data, null)
        }

        fun <T> error(error: CalendarError, data: T?): DataHolder<T> {
            return DataHolder(RepositoryStatus.ERROR, data, error)
        }

        fun <T> loading(): DataHolder<T> {
            return DataHolder(RepositoryStatus.LOADING, null, null)
        }
    }
}
