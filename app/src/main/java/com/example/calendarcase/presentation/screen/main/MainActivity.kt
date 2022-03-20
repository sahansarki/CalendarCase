package com.example.calendarcase.presentation.screen.main

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.calendarcase.databinding.ActivityMainBinding
import com.example.calendarcase.enum.Month
import com.example.calendarcase.enum.RepositoryStatus
import com.example.calendarcase.extension.dateConvert
import com.example.calendarcase.presentation.adapter.NotesRecyclerAdapter
import com.example.calendarcase.presentation.screen.popup.BottomSheetFragment
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
    private lateinit var noteAdapter: NotesRecyclerAdapter
    private lateinit var dateString: String

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        activityBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(activityBinding.root)


        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        dateString = dateConvert(year, month, day)

        noteAdapter = NotesRecyclerAdapter({ updatedNote ->

            val bottomSheet = BottomSheetFragment.newInstance(updatedNote, {
                mViewModel.getNoteByDate(it)
            }) {

            }
            bottomSheet.show(
                supportFragmentManager,
                BottomSheetFragment.TAG
            )
        }, { deletedNote ->
            mViewModel.deleteNote(deletedNote) {
                mViewModel.getNoteByDate(dateString)
            }
        })

        val mDividerItemDecoration =
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        with(activityBinding.noteListRc) {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = noteAdapter
            addItemDecoration(mDividerItemDecoration)
            setHasFixedSize(true)
        }


        mViewModel.getNoteByDate(dateString)


        activityBinding.floatingActionButton.setOnClickListener {
            val bottomSheet = BottomSheetFragment.newInstance(updateDone = {
                mViewModel.getNoteByDate(dateString)
            }, insertDone = {
                mViewModel.getNoteByDate(dateString)
            })

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
        observeNote()
    }


    private fun observeNote() {
        mViewModel.note.observe(this) {
            when (it.status) {
                RepositoryStatus.OK -> {
                    val noteList = it.data!!
                    noteAdapter.differ.submitList(noteList)
                }

                RepositoryStatus.ERROR -> {
                    Toast.makeText(this, it.error!!.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }


}