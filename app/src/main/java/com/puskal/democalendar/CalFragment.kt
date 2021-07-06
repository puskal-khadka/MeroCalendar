package com.puskal.democalendar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.puskal.democalendar.databinding.FragmentCalBinding
import com.puskal.merocalendar.DateClickListener
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel


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



        binding.mCalendarView.setOnDateClickListener(
            object:DateClickListener{
                override fun onDateClick(dateModel: DateModel) {
                    Log.d("d","datemodel ${dateModel.formattedAdDate}")
                }

            }
        )
        binding.mCalendarView.apply {
            setCalendarType(CalendarType.AD)
            setLanguage(LocalizationType.ENGLISH_US)
            initCalendar()
        }

        /**If you want to show event in calendar
         * get event from api and MAP your event into Event Data Class/Model of Merocalendar Library
         * Here for the shake of demo, i create fake object
         */
        val fakeEventObject=FakeEventData.eventList

        binding.mCalendarView.setEvent(fakeEventObject)




        return binding.root
    }


}