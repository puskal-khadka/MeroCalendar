package com.puskal.democalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.puskal.democalendar.databinding.FragmentCalBinding
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType


class CalFragment : Fragment() {
private lateinit var binding: FragmentCalBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_cal, container, false)

        binding.mCalendarView.apply {
            setCalendarType(CalendarType.AD)
            setLanguage(LocalizationType.NEPALI_NP)
            initCalendar()
        }
        return binding.root
    }


}