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
import com.example.calendarcase.R
import com.example.calendarcase.databinding.FragmentBottomSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.util.*

class BottomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var bottomSheetBinding: FragmentBottomSheetBinding

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

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        bottomSheetBinding.dateText.setOnClickListener {
            val datePicker = DatePickerDialog(requireContext(), DatePickerDialog.OnDateSetListener { view, mYear, mMonth, mDay ->
                bottomSheetBinding.dateText.text = "$mDay / ${mMonth + 1} / $mYear"
            }, year, month, day)
            datePicker.show()
        }

        bottomSheetBinding.hourText.setOnClickListener {
            val timePicker = TimePickerDialog(requireContext(), TimePickerDialog.OnTimeSetListener { view, mHour, mMinute ->
                bottomSheetBinding.hourText.text = "$mHour:$mMinute"
            }, hour, minute, true)
            timePicker.show()
        }
    }

}