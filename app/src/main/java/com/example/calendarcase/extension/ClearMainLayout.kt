package com.example.calendarcase.extension

import android.view.View
import com.example.calendarcase.databinding.ActivityMainBinding

fun clearMainLayout(view: ActivityMainBinding){
    view.apply {
        editTextTitle.visibility = View.GONE
        editTextDescription.visibility = View.GONE
        imageDescription.visibility = View.GONE
        imageClock.visibility = View.GONE
        allDayText.visibility = View.GONE
        allDaySwitchButton.visibility = View.GONE
        dateText.visibility = View.GONE
        hourText.visibility = View.GONE
        imageReplay.visibility = View.GONE
        replayText.visibility = View.GONE
        replaySwitchButton.visibility = View.GONE
        buttonDelete.visibility = View.GONE
    }
}

fun viewMainLayout(view: ActivityMainBinding){
    view.apply {
        editTextTitle.visibility = View.VISIBLE
        editTextDescription.visibility = View.VISIBLE
        imageDescription.visibility = View.VISIBLE
        imageClock.visibility = View.VISIBLE
        allDayText.visibility = View.VISIBLE
        allDaySwitchButton.visibility = View.VISIBLE
        dateText.visibility = View.VISIBLE
        hourText.visibility = View.VISIBLE
        imageReplay.visibility = View.VISIBLE
        replayText.visibility = View.VISIBLE
        replaySwitchButton.visibility = View.VISIBLE
        buttonDelete.visibility = View.VISIBLE
    }
}