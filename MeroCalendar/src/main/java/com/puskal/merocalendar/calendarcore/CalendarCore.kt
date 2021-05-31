package com.puskal.merocalendar.calendarcore


import android.util.Log
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.IO
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.puskal.merocalendar.calendarcore.miti.Date
import com.puskal.merocalendar.calendarcore.miti.DateUtils

/**
 * All the logic related to nepali calendar is isolated here.
 * Feel free to modify as per the current changes in requirements. ;)
 */
class CalendarCore {

    companion object {
        const val NUMBER_OF_DAYS_IN_WEEK = 7
        const val TAG="meroCalendar"
    }

    // holds the last prepared ( currently active ) date.
    private val mSessionDateItems: ArrayList<DateItem> = arrayListOf()

    // nepali date for today
    private val mTodayDate: Date = Date(Calendar.getInstance()).convertToNepali()
    private lateinit var mMeta: DateItem.Meta

    // we should not reference it directly because well JVM :D
    // will be modified directly in the referencing memory
    private var mSessionDate = mTodayDate.convertToEnglish()

    init {

        // prepare date list for this month
        // when the object is created
        runBlocking {
            mSessionDateItems.clear()
            getFor(mTodayDate.convertToEnglish(), true)
        }

        Log.d("d","we have date $mSessionDate and $mTodayDate")
    }

    fun getSessionDate(): Date? {
        return mSessionDate
    }

    fun getCurrent(): ArrayList<DateItem> {
        return mSessionDateItems
    }

    /**
     *
     * @param date the date item which contains year month information
     * @param markPassedDay if set true wll mark the day on [date]
     */
    private suspend fun getFor(date: Date, markPassedDay: Boolean = false): ArrayList<DateItem> = suspendCoroutine {
        val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
            // will get triggered if the date trices to go over 2090
            Log.e(TAG, "Ooops we cannot go beyond this point.")
        }
        CoroutineScope(IO).launch(exceptionHandler) {
            withContext(IO) {

                // calculate everything here and continue when the data is ready

                val list = ArrayList<DateItem>()

                // prepare days
                list.add(DateItem("Sun"))
                list.add(DateItem("Mon"))
                list.add(DateItem("Tue"))
                list.add(DateItem("Wed"))
                list.add(DateItem("Thu"))
                list.add(DateItem("Fri"))
                list.add(DateItem("Sat"))

                val todayInBs = Date(Calendar.getInstance()).convertToNepali()
                // today's date in ad
                // today's date in bs
                val nepaliDate = date.convertToNepali()
                // 1 gatey in ad
                val nepMonthSuruVayekoEnglishDate =
                        Date(nepaliDate.year, nepaliDate.month, 1).convertToEnglish()

                //puskal
                val nepaliMonthEndVayekoEnglishDate=Date(nepaliDate.year,nepaliDate.month,30).convertToEnglish()

                // number of days this month in bs
                val numberOfDaysInMonth = DateUtils.getNumDays(nepaliDate.year, nepaliDate.month)

                var saturdayIndex = 8 - nepMonthSuruVayekoEnglishDate.weekDayNum

                for (i in (2 - nepMonthSuruVayekoEnglishDate.weekDayNum)..numberOfDaysInMonth) {

                    val model = DateItem("$i", "${nepaliDate.month}", "${nepaliDate.year}")

                    // set the holiday of the current date
                    // if holiday the date will appear in red
                    model.isHoliday = if (saturdayIndex == i) {
                        saturdayIndex += NUMBER_OF_DAYS_IN_WEEK
                        true
                    } else false

                    if (i >= 1) {
                        // clickable only if the model contains actual date
                        model.isClickable = true
                        model.month = nepaliDate.month.toString()
                        model.year = nepaliDate.year.toString()
                    }

                    // select passed date's day by default
                    if (
                            markPassedDay && date.convertToNepali().day == i
                    ) {
                        model.isSelected = true
                    }

                    // convert to nepali and assign today's day
                    // convert it back to english
                    val convertedAd = date.convertToNepali().apply {
                        day = i
                    }.convertToEnglish()
                    convertedAd.apply {
                        model.adYear = this.year.toString()
                        model.adMonth = this.month.toString()
                        model.adDate = this.day.toString()
                    }

                    Log.d(TAG,"actual : $date converted : $convertedAd")

                    // check if the specified date is
                    model.isToday = todayInBs.day == i
                            && model.year == todayInBs.year.toString()
                            && model.month == todayInBs.month.toString()

                    list.add(model)

                    Log.d(TAG,"we are adding $model")

                }

                var currentCalendarEnglishDate=""
                val startYear=nepMonthSuruVayekoEnglishDate.year
                 val endYear=nepaliMonthEndVayekoEnglishDate.year
                if (startYear == endYear) {
                    currentCalendarEnglishDate="$startYear AD"
                }
                else{
                    currentCalendarEnglishDate="$startYear/$endYear AD"
                }


                mSessionDateItems.clear()
                mSessionDateItems.addAll(list)

                // also adjust meta data
                val mon1 = DateUtils.getMonthNameAd(list[15].adMonth.toInt())
                val mon2 = DateUtils.getMonthNameAd(list[35].adMonth.toInt())
                mMeta = DateItem.Meta(
                        year = "${nepaliDate.year} BS  (${currentCalendarEnglishDate})",
                        month = "${DateUtils.getMonthName(nepaliDate.month)} ( $mon1 / $mon2 )"
                )

                // resume with whatevery the program was doing
                it.resume(list)
            }
        }
    }

    fun getNextMonth(): ArrayList<DateItem> {
        return runBlocking {
            mSessionDate.month = mSessionDate.month + 1
            return@runBlocking getFor(mSessionDate, false)
        }
    }

    fun getNextYear(): ArrayList<DateItem> {
        return runBlocking {
            Log.d(TAG,"this date -> $mSessionDate")
            mSessionDate.year = mSessionDate.year + 1
            return@runBlocking getFor(mSessionDate, false)
        }
    }

    fun getPrevMonth(): ArrayList<DateItem> {
        return runBlocking {
            mSessionDate.month = mSessionDate.month - 1
            return@runBlocking getFor(mSessionDate, false)
        }
    }

    fun getPrevYear(): ArrayList<DateItem> {
        return runBlocking {
            mSessionDate.year = mSessionDate.year - 1
            return@runBlocking getFor(mSessionDate, false)
        }
    }

    fun getMeta(): DateItem.Meta {

        if (!::mMeta.isInitialized) {
            mMeta = DateItem.Meta(year = mSessionDate.year.toString(), month = mSessionDate.month.toString())
        }

        return mMeta
    }

}