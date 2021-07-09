package com.puskal.democalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.puskal.democalendar.databinding.FragmentHorizontalCalendarBinding
import com.puskal.merocalendar.DateClickListener
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel

class HorizontalCalendarFragment : Fragment() {

    private lateinit var binding: FragmentHorizontalCalendarBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_horizontal_calendar, container, false)


        val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                binding.tvCurrentDate.text = dateModel.formattedAdDate
            }
        }



        binding.horizontalCalendarView.setCalendarType(CalendarType.AD)
            .setLanguage(LocalizationType.ENGLISH_US)
            .setOnDateClickListener(dateClickListener)
            .build()


        with(binding) {
            ivNext.setOnClickListener {
                horizontalCalendarView.setNextMonthDate()
            }


            ivPrevious.setOnClickListener {
                horizontalCalendarView.setPreviousMonthDate()
            }

        }




        return  binding.root
    }

}