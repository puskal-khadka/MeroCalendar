package com.puskal.merocalendar

import android.util.Log
import com.puskal.merocalendar.calendarcore.LocalizationHelper
import com.puskal.merocalendar.calendarcore.LocalizationHelper.weekNameInEnglish
import com.puskal.merocalendar.calendarcore.LocalizationHelper.weekNameInNepali
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel
import java.text.SimpleDateFormat
import java.util.*

/** created by Puskal khadka
 * 4 july, 2021
 */
object CalendarController {
    private var month: Int = 0
    private var year: Int = 0

     var currentMonthDateList = arrayListOf<DateModel>()
        get() = field
        set(dateList) {
            field.clear()
            field.addAll(dateList)
        }

    fun getDateList(
        calendarType: CalendarType,
        localizationType: LocalizationType, month: Int, year: Int
    ): Pair<ArrayList<DateModel>, String> {
        currentMonthDateList.clear()
        this.month = month
        this.year = year
        return when (calendarType) {
            CalendarType.AD -> {
                val (dateList,title) = englishCalendarController(localizationType)
                currentMonthDateList=dateList
                Pair(dateList,title)
            }
            else -> englishCalendarController(localizationType)
        }

    }


    fun englishCalendarController(localizationType: LocalizationType): Pair<ArrayList<DateModel>, String> {
        val calendar = Calendar.getInstance()
        Log.d("meroCalendar", "show $year  $month")
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        Log.d(
            "mero",
            "liek me d${
                LocalizationHelper.englishMonthNameInEnglishFont(
                    calendar.get(Calendar.MONTH).plus(1)
                )
            }, ${
                calendar.get(
                    Calendar.YEAR
                )
            }"
        )
        val monthNum = calendar.get(Calendar.MONTH).plus(1)
        val year = calendar.get(Calendar.YEAR)
        val monthYearTitle = when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                "${LocalizationHelper.englishMonthNameInEnglishFont(monthNum)}, $year"
            }
            else -> {
                LocalizationHelper.toNepaliDigit(
                    "${
                        LocalizationHelper.englishMonthNameInNepaliFont(
                            monthNum
                        )
                    }, $year"
                )
            }
        }


        val dateList = when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                arrayListOf<DateModel>(
                    DateModel(weekNameInEnglish(1)),
                    DateModel(weekNameInEnglish(2)),
                    DateModel(weekNameInEnglish(3)),
                    DateModel(weekNameInEnglish(4)),
                    DateModel(weekNameInEnglish(5)),
                    DateModel(weekNameInEnglish(6)),
                    DateModel(weekNameInEnglish(7)),
                )
            }
            else -> {
                arrayListOf(
                    DateModel(weekNameInNepali(1)),
                    DateModel(weekNameInNepali(2)),
                    DateModel(weekNameInNepali(3)),
                    DateModel(weekNameInNepali(4)),
                    DateModel(weekNameInNepali(5)),
                    DateModel(weekNameInNepali(6)),
                    DateModel(weekNameInNepali(7)),
                )
            }
        }

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

        Log.d(
            EventCalendarFragment.TAG,
            "month is $month  and year is $year  and total  days in this month $daysInThisMonth  and first week day of month $weekNumberOfFirstDayOfMonth "
        )

        for (i in 1..daysInThisMonth) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val thisDateString = "$year-${month + 1}-$i"
            val date = sdf.parse(thisDateString)

            val dispDay =
                if (localizationType == LocalizationType.ENGLISH_US) i.toString() else LocalizationHelper.toNepaliDigit(
                    i.toString()
                )
            val dateModel =
                DateModel(
                    dispDay,formattedAdDate = thisDateString,
                    adDate = date,
                    isToday = Calendar.getInstance().time == date
                )
            if (sdf.format(Calendar.getInstance().time) == sdf.format(date)) {
                dateModel.hasEvent = true
                dateModel.eventColorCode = "#76BF4E"
            }
            dateList.add(dateModel)

        }

        return Pair(dateList, monthYearTitle)


    }


}