package com.example.calendarcase.presentation.screen.popup

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.example.calendarcase.R
import com.example.calendarcase.databinding.FragmentBottomSheetBinding
import com.example.calendarcase.domain.model.Note
import com.example.calendarcase.enum.Month
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@AndroidEntryPoint
class BottomSheetFragment : BottomSheetDialogFragment() {
    // private val mViewModel: QuotesViewModel by viewModels()
    private lateinit var bottomSheetBinding: FragmentBottomSheetBinding
    private val mViewModel: BottomSheetFragmentViewModel by viewModels()

    companion object {
        const val TAG = "SearchScreenBottomSheet"
        fun newInstance(): BottomSheetFragment {
            return BottomSheetFragment()
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
                    val dayOfWeekFormatter: DateTimeFormatter =
                        DateTimeFormatter.ofPattern("EEE", Locale.ENGLISH)

                    val date: LocalDate = LocalDate.of(
                        mYear, 3, mDay
                    )
                    val dateString = "${date.format(dayOfWeekFormatter)}, ${Month.values()[mMonth].name} $mDay, $mYear"
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
                val note = Note(
                    title = bottomSheetBinding.editTextTitle.text.toString(),
                    description = bottomSheetBinding.editTextDescription.text.toString(),
                    allDay = bottomSheetBinding.allDaySwitchButton.isChecked,
                    date = bottomSheetBinding.dateText.text.toString(),
                    time = bottomSheetBinding.hourText.text.toString(),
                )
                mViewModel.insertNote(note)
            }
        }
    }


}