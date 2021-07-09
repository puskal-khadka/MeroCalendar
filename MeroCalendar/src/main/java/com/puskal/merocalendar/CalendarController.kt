package com.puskal.merocalendar

import android.util.Log
import com.puskal.merocalendar.calendarcore.LocalizationHelper
import com.puskal.merocalendar.calendarcore.LocalizationHelper.weekNameInEnglish
import com.puskal.merocalendar.calendarcore.LocalizationHelper.weekNameInNepali
import com.puskal.merocalendar.calendarcore.miti.Date
import com.puskal.merocalendar.calendarcore.miti.DateUtils
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

/** created by Puskal khadka
 * 4 july, 2021
 */
object CalendarController {
    private var month: Int = 0  //month value: 1 to 12
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
            else -> {
                val (dateList,title) = nepaliCalendarController(localizationType)
                currentMonthDateList=dateList
                Pair(dateList,title)
            }
        }

    }


    fun nepaliCalendarController(localizationType: LocalizationType): Pair<ArrayList<DateModel>, String> {

        val dateList = weekNameDateModel(localizationType)

        val todayNepaliDate = DateUtils.getNepaliDate(Date(Calendar.getInstance()))
        val nepaliDate= Date(year,month,1)

        val monthYearTitle = when (localizationType) {
            LocalizationType.ENGLISH_US -> {
                "${LocalizationHelper.nepaliMonthNameInEngFont(month)}, $year"
            }
            else -> {
                LocalizationHelper.toNepaliDigit("${LocalizationHelper.nepaliMonthNameInNepaliFont(month)}, $year")
            }
        }

        val nepaliFirstMonthDayInEnglish = DateUtils.getEnglishDate(Date(nepaliDate.year, nepaliDate.month, 1))
        val numberOfDaysInMonth = DateUtils.getNumDays(nepaliDate.year, nepaliDate.month)
        val weekNumberOfFirstDayOfNepaliMonth = nepaliFirstMonthDayInEnglish.weekDayNum

        for (i in 1 until weekNumberOfFirstDayOfNepaliMonth) {
            dateList.add(DateModel(""))
        }

        var thisDayWeekNumber=weekNumberOfFirstDayOfNepaliMonth

        for (i in 1..numberOfDaysInMonth) {
            nepaliDate.day = i
            val convertedAdDate = DateUtils.getEnglishDate(nepaliDate)
            val formattedEnglishDate="${convertedAdDate.year}-${convertedAdDate.month}-${convertedAdDate.day}"
            val adDate=SimpleDateFormat("yyyy-MM-dd").parse(formattedEnglishDate)
            val displayDay=if(localizationType==LocalizationType.ENGLISH_US) i.toString() else LocalizationHelper.toNepaliDigit(i.toString())
            val dateModel = DateModel(
                displayDay,
                formattedAdDate = formattedEnglishDate,
                formattedBsDate = "${nepaliDate.year}-${nepaliDate.month}-${nepaliDate.day}",
                adDate =  adDate,
                todayWeekDay = thisDayWeekNumber++
            )

            if (todayNepaliDate.year == year && todayNepaliDate.month == month && todayNepaliDate.day == i) {
                dateModel.hasEvent=true
                dateModel.eventColorCode="#76BF4E"

            }


            dateList.add(dateModel)
            Log.d(EventCalendarFragment.TAG,"dateModel is  $i adn  ${dateModel.formattedAdDate} and ${dateModel.formattedBsDate}")


        }

        return Pair(dateList, monthYearTitle)



    }



    fun englishCalendarController(localizationType: LocalizationType): Pair<ArrayList<DateModel>, String> {
        val calendar = Calendar.getInstance()
        Log.d("meroCalendar", "show $year  $month")
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month.minus(1))
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


        val dateList = weekNameDateModel(localizationType)

        val daysInThisMonth = calendar.getActualMaximum(Calendar.DATE)

        calendar.set(Calendar.DAY_OF_MONTH, 1)
        val weekNumberOfFirstDayOfMonth = calendar.get(Calendar.DAY_OF_WEEK)

        for (i in 1 until weekNumberOfFirstDayOfMonth) {
            dateList.add(DateModel(""))
        }



        var thisDayWeekNumber=weekNumberOfFirstDayOfMonth

        Log.d(
            EventCalendarFragment.TAG,
            "month is $month  and year is $year  and total  days in this month $daysInThisMonth  and first week day of month $weekNumberOfFirstDayOfMonth "
        )

        for (i in 1..daysInThisMonth) {
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val thisDateString = "$year-${month}-$i"
            val date = sdf.parse(thisDateString)

            val dispDay =
                if (localizationType == LocalizationType.ENGLISH_US) i.toString() else LocalizationHelper.toNepaliDigit(
                    i.toString()
                )
            val dateModel =
                DateModel(
                    dispDay,formattedAdDate = thisDateString,
                    adDate = date,
                    todayWeekDay = thisDayWeekNumber++
                )
            if (sdf.format(Calendar.getInstance().time) == sdf.format(date)) {
                dateModel.hasEvent = true
                dateModel.isToday=true
                dateModel.eventName="Today"
                dateModel.eventColorCode = "#76BF4E"
            }
            dateList.add(dateModel)

        }

        return Pair(dateList, monthYearTitle)


    }


    fun weekNameDateModel(localizationType: LocalizationType) : ArrayList<DateModel>{
        return when (localizationType) {
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

    }


}