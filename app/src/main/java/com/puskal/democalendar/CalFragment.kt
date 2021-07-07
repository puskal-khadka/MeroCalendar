package com.puskal.democalendar

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.puskal.democalendar.databinding.FragmentCalBinding
import com.puskal.merocalendar.DateClickListener
import com.puskal.merocalendar.MonthChangeListener
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_cal, container, false)



        /**If you want to show event in calendar
         * get event from api and MAP your event into Event Data Class/Model of Merocalendar Library
         * Here for the shake of demo, i create fake object
         */
        val fakeEventList = FakeEventData.eventList

       val dateClickListener  = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.d("d", "datemodel ${dateModel.formattedAdDate}")
            }
        }

        val monthChangeListener = object: MonthChangeListener{
            override fun onMonthChange(adYear: Int, adMonth: Int) {
              //if your api get event monthwise, or want to show event monthise, pass event of this month from here
                //adyear-> 2021
                //admonth(1-12), july=7
                //for example i want to pass same fake object here on month change
                binding.mCalendarView.setEvent(fakeEventList)
            }
        }


        binding.mCalendarView.setCalendarType(CalendarType.BS)
            .setLanguage(LocalizationType.NEPALI_NP)
            .setOnDateClickListener(dateClickListener)
            .setOnMonthChangeListener(monthChangeListener)
            .setEvent(fakeEventList)  //you can also add event separately like inside api response function such as binding.mCalendarView.setEvent(eventResponse)
            .build()


        return binding.root
    }


}