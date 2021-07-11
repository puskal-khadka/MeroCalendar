package com.puskal.democalendar

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.puskal.democalendar.databinding.DemoCalendarConfigurationBinding
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
                Log.d("d","data is ${dateModel.localizedFormattedDate}")
                binding.tvCurrentDate.text =  dateModel.localizedFormattedDate
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

















        /*********
         * For Calendar type & language Select Demo
         * Same as above,
         */

        var calendarType = CalendarType.AD
        var language = LocalizationType.ENGLISH_US
        var alert: AlertDialog? = null

        val dialogBuilder = AlertDialog.Builder(requireContext())
        val configBinding: DemoCalendarConfigurationBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.demo_calendar_configuration,
            null,
            false
        )


        with(configBinding) {
            rbAd.isChecked=true
            rbEn.isChecked=true
            rgCalendarType.setOnCheckedChangeListener { group, checkedId ->
                calendarType = when (checkedId) {
                    R.id.rbBs -> CalendarType.BS
                    else -> CalendarType.AD
                }
                binding.horizontalCalendarView.setCalendarType(calendarType)
                    .setLanguage(language)
                    .setOnDateClickListener(dateClickListener)
                    .build()
            }
            rgLanugage.setOnCheckedChangeListener { group, checkedId ->
                language = when (checkedId) {
                    R.id.rbNp -> LocalizationType.NEPALI_NP
                    else -> LocalizationType.ENGLISH_US
                }
                binding.horizontalCalendarView.setCalendarType(calendarType)
                    .setLanguage(language)
                    .setOnDateClickListener(dateClickListener)
                    .build()
            }
            btnOk.setOnClickListener {
                alert?.dismiss()
            }
        }
        dialogBuilder.apply {
            setView(configBinding.root)
            setCancelable(false)
        }
        alert = dialogBuilder.create()
        alert.show()



        return  binding.root
    }

}