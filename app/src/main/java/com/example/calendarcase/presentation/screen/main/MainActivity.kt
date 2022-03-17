package com.example.calendarcase.presentation.screen.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.fragment.app.viewModels
import com.example.calendarcase.databinding.ActivityMainBinding
import com.example.calendarcase.enum.Month
import com.example.calendarcase.enum.RepositoryStatus
import com.example.calendarcase.extension.clearMainLayout
import com.example.calendarcase.extension.viewMainLayout
import com.example.calendarcase.presentation.screen.popup.BottomSheetFragment
import com.example.calendarcase.presentation.screen.popup.BottomSheetFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var activityBinding: ActivityMainBinding
    private val mViewModel: MainActivityViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.O)
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

        activityBinding.calendarView.setOnDateChangeListener { picker, mYear, mMonth, mDay ->
            val dayOfWeekFormatter: DateTimeFormatter =
                DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)

            val date: LocalDate = LocalDate.of(
                mYear, 3, mDay
            )
            val dateString =
                "${date.format(dayOfWeekFormatter)}, ${Month.values()[mMonth].name} $mDay, $mYear"
            mViewModel.getNoteByDate(dateString)
        }

        activityBinding.buttonDelete.setOnClickListener {
            if(activityBinding.dateText.visibility == View.VISIBLE) {
                mViewModel.deleteNote(activityBinding.note!!){
                    Toast.makeText(this, it, Toast.LENGTH_LONG).show()
                    clearMainLayout(activityBinding)
                }
            }
        }
        observeNote()
    }


    private fun observeNote() {
        mViewModel.note.observe(this) {
            when (it.status) {
                RepositoryStatus.OK -> {
                    val note = it.data!!
                    viewMainLayout(activityBinding)
                    activityBinding.note = note
                    activityBinding.allDaySwitchButton.isChecked = note.allDay
                    activityBinding.replaySwitchButton.isChecked = note.doesRepeat
                    activityBinding.allDaySwitchButton.isClickable = false
                    activityBinding.replaySwitchButton.isClickable = false
                }

                RepositoryStatus.ERROR -> {
                    clearMainLayout(activityBinding)
                    Toast.makeText(this, it.error!!.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}