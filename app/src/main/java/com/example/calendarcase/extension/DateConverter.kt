package com.example.calendarcase.extension

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.calendarcase.enum.Month
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@RequiresApi(Build.VERSION_CODES.O)
fun dateConvert(year: Int, month: Int, day: Int): String {
    val dayOfWeekFormatter: DateTimeFormatter =
        DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)

    val date: LocalDate = LocalDate.of(
        year, month, day
    )
    return "${date.format(dayOfWeekFormatter)}, ${Month.values()[month].name} $date, $year"
}