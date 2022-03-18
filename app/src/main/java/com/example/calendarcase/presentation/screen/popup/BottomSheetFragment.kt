package com.example.calendarcase.presentation.screen.popup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.calendarcase.R
import com.example.calendarcase.databinding.FragmentBottomSheetBinding
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.extension.dateConvert
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import java.util.*

@OptIn(DelicateCoroutinesApi::class)
@AndroidEntryPoint
class BottomSheetFragment(private val note: Note? = null, private val updateDone: (date: String) -> Unit) : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: FragmentBottomSheetBinding
    private val mViewModel: BottomSheetFragmentViewModel by viewModels()

    companion object {
        const val TAG = "SearchScreenBottomSheet"
        fun newInstance(note: Note? = null, updateDone: (date: String) -> Unit): BottomSheetFragment {
            return BottomSheetFragment(note, updateDone)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.ThemeOverlay_BottomSheetDialog)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        bottomSheetBinding = FragmentBottomSheetBinding.inflate(layoutInflater)
        return bottomSheetBinding.root
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (note != null) initializeLayout()

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)


        bottomSheetBinding.dateText.setOnClickListener {
            val datePicker = DatePickerDialog(
                requireContext(),
                { picker, mYear, mMonth, mDay ->
                    val dateString = dateConvert(mYear, mMonth, mDay)
                    bottomSheetBinding.dateText.text = dateString
                },
                year,
                month,
                day
            )
            datePicker.show()
        }

        bottomSheetBinding.hourText.setOnClickListener {
            val timePicker = TimePickerDialog(
                requireContext(),
                { view, mHour, mMinute ->
                    bottomSheetBinding.hourText.text = "$mHour:$mMinute"
                },
                hour,
                minute,
                true
            )
            timePicker.show()
        }

        bottomSheetBinding.saveButton.setOnClickListener {
            if (bottomSheetBinding.editTextTitle.text.isNotEmpty() &&
                bottomSheetBinding.editTextDescription.text.isNotEmpty()
            ) {

                val newNote = Note(
                    title = bottomSheetBinding.editTextTitle.text.toString(),
                    description = bottomSheetBinding.editTextDescription.text.toString(),
                    allDay = bottomSheetBinding.allDaySwitchButton.isChecked,
                    date = bottomSheetBinding.dateText.text.toString(),
                    time = bottomSheetBinding.hourText.text.toString(),
                    doesRepeat = bottomSheetBinding.replaySwitchButton.isChecked
                )

                if (note != null) {
                    newNote.id = note.id
                    mViewModel.updateNote(newNote) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                    updateDone(note.date)

                } else {
                    mViewModel.insertNote(newNote) {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
                    }
                }

                this.dismiss()
            }
        }
    }

    private fun initializeLayout() {
        bottomSheetBinding.apply {
            editTextTitle.setText(note!!.title)
            editTextDescription.setText(note.description)
            allDaySwitchButton.isChecked = note.allDay
            dateText.text = note.date
            hourText.text = note.time
            replaySwitchButton.isChecked = note.doesRepeat
        }
    }


}