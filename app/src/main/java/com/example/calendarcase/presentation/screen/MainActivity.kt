package com.example.calendarcase.presentation.screen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendarcase.R
import com.example.calendarcase.databinding.ActivityMainBinding
import com.example.calendarcase.presentation.screen.popup.BottomSheetFragment

class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)

        activityBinding.floatingActionButton.setOnClickListener {
            val bottomSheet = BottomSheetFragment.newInstance()
            bottomSheet.show(
                supportFragmentManager,
                BottomSheetFragment.TAG
            )
        }
    }
}