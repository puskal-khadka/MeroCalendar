package com.puskal.merocalendar

import android.util.Log
import com.puskal.merocalendar.databinding.EnglishEventCalendarFragmentBinding
import com.puskal.merocalendar.model.DateModel
import java.text.SimpleDateFormat
import java.util.*

/**puskal khadka
 * This is controller for showing nepali calendar and english calendar
 */
object CalendarUtils {
    private var month: Int = 0
    private var year: Int = 0
    private lateinit var calendar: Calendar
    private lateinit var binding: EnglishEventCalendarFragmentBinding


    fun showCalendar(binding: EnglishEventCalendarFragmentBinding) {
        this.binding=binding
        englishCalendarController()
    }

    fun englishCalendarController() {
        calendar = Calendar.getInstance()
        month = calendar.get(Calendar.MONTH)
        year = calendar.get(Calendar.YEAR)
        englishCalendar()
        with(binding) {
            ivArrowLeft.setOnClickListener {
                if (month == 0) {
                    month = 11
                    year = year - 1
                } else {
                    month--
                }
                englishCalendar()
            }

            ivArrowRight.setOnClickListener {
                if (month == 11) {
                    month = 0
                    year = year + 1
                } else
                    month++
                englishCalendar()
            }

        }
    }
    fun englishCalendar():List<DateModel> {
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)

        binding.tvDate.text = "${monthName[calendar.get(Calendar.MONTH)]}, ${calendar.get(Calendar.YEAR)}"

        val dateList = arrayListOf<DateModel>(
            DateModel("Sun"),
            DateModel("Mon"),
            DateModel("Tue"),
            DateModel("Wed"),
            DateModel("Thu"),
            DateModel("Fri"),
            DateModel("Sat")
        )

        val daysInThisMonth = calendar.getActualMaximum(Calendar.DATE)

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val weekNumberOfFirstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until weekNumberOfFirstDayOfMonth) {
            dateList.add(DateModel(""))
        }


        val testCalendar = Calendar.getInstance()
        testCalendar.set(Calendar.YEAR, 1990)
        testCalendar.set(Calendar.MONTH, 0)
        testCalendar.set(Calendar.DAY_OF_MONTH, 1)

        Log.d("meroCalendar","month is $month  and year is $year  and total  days in this month $daysInThisMonth  and first week day of month $weekNumberOfFirstDayOfMonth ")

        for (i in 1..daysInThisMonth) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val thisDateString = "$year-${month + 1}-$i"
            val date = sdf.parse(thisDateString)

            val dateModel =
                DateModel(i.toString(), adDate = date, isToday = Calendar.getInstance().time == date)
            if (sdf.format(Calendar.getInstance().time) == sdf.format(date)) {
                dateModel.hasEvent = true
                dateModel.eventColor = R.color.accent_color
            }
            dateList.add(dateModel)

        }

        return dateList

    }


    fun nepaliCalendarController() {

    }

    fun nepaliCalendar() {

    }


    val monthName = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )

}