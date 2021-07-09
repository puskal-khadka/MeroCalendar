package com.puskal.merocalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.puskal.merocalendar.calendarcore.miti.Date
import com.puskal.merocalendar.calendarcore.miti.DateUtils
import com.puskal.merocalendar.databinding.LayoutHorizontalCalendarBinding
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel
import com.puskal.merocalendar.model.EventModel
import java.util.*

/**@author Puskal Khadka
 * 8 july, 2021
 */
class HorizontalMeroCalendarView : LinearLayout {
    private var calendarType: CalendarType = CalendarType.AD
    private var language: LocalizationType = LocalizationType.ENGLISH_US
    private lateinit var binding: LayoutHorizontalCalendarBinding
    private var eventList: ArrayList<EventModel> = arrayListOf()
    private var monthChangeListener: MonthChangeListener? = null
    private var dateClickListener: DateClickListener? = null
    private var currentMonthDateList = arrayListOf<DateModel>()


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadUi(context, attrs)
    }


    private fun loadUi(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_horizontal_calendar, this, true)

    }


    fun setCalendarType(type: CalendarType): HorizontalMeroCalendarView {
        this.calendarType = type
        return this
    }

    fun setLanguage(lan: LocalizationType): HorizontalMeroCalendarView {
        this.language = lan
        return this
    }


    fun setOnMonthChangeListener(listener: MonthChangeListener): HorizontalMeroCalendarView {
        monthChangeListener = listener
        return this
    }

    fun setOnDateClickListener(listener: DateClickListener): HorizontalMeroCalendarView {
        dateClickListener = listener
        return this
    }


    private val horizontalCalendarAdapter: HorizontalCalendarAdapter by lazy {
        HorizontalCalendarAdapter(language, dateClickListener)
    }


    var currentMonth = 0
    var currentYear = 0
    var originalCurrentMonth = 0
    var originalCurrentYear = 0
    private fun todayMonthYear(calendarInstance: Calendar): Pair<Int, Int> {
        val calendarInstance = Calendar.getInstance()
        when (calendarType) {
            CalendarType.AD -> {
                currentMonth = calendarInstance.get(Calendar.MONTH).plus(1)
                currentYear = calendarInstance.get(Calendar.YEAR)
            }
            else -> {
                val todayNepaliDate = DateUtils.getNepaliDate(Date(calendarInstance))
                currentMonth = todayNepaliDate.month
                currentYear = todayNepaliDate.year
            }
        }
        return Pair(currentMonth, currentYear)
    }

    private val linearLayoutManager: LinearLayoutManager by lazy {
        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }
    val calendar = Calendar.getInstance()

    private fun initCalendar() {
        val todayMonthYrs = todayMonthYear(calendar)
        currentMonth = todayMonthYrs.first
        currentYear = todayMonthYrs.second

        originalCurrentYear = currentYear
        originalCurrentMonth = currentMonth

        setAdapter(currentMonth, currentYear, MONTH_CHANGE_CURRENT)

        binding.rvHorizontalCalendar.apply {
            adapter = horizontalCalendarAdapter
            layoutManager = linearLayoutManager
        }

    }


    fun setNextMonthDate() {
        if (currentMonth == 12) {
            currentMonth = 1
            currentYear += 1
        } else {
            currentMonth += 1
        }
        setAdapter(currentMonth, currentYear, MONTH_CHANGE_NEXT)
    }

    fun setPreviousMonthDate() {
        if (currentMonth == 1) {
            currentMonth = 12
            currentYear -= 1
        } else {
            currentMonth -= 1
        }
        setAdapter(currentMonth, currentYear, MONTH_CHANGE_PREVIOUS)
    }


    private fun setAdapter(currentMonth: Int, currentYear: Int, monthChangeStatus: Int) {
        val (dateList, title) = CalendarController.getDateList(
            calendarType,
            language,
            currentMonth,
            currentYear
        )
        currentMonthDateList.clear()
        currentMonthDateList.addAll(dateList)

        val finalDateList = currentMonthDateList.filter { it.todayWeekDay != 0 }

        var scrollToPos = 0
        if (currentMonth == originalCurrentMonth && currentYear == originalCurrentYear) {
            val currentDayIndex=finalDateList.indexOfFirst { it.isToday }
            horizontalCalendarAdapter.addItem(finalDateList, MONTH_CHANGE_CURRENT,currentDayIndex)
            if (currentDayIndex > 3) {
                scrollToPos = currentDayIndex-3
            }
        } else {
            horizontalCalendarAdapter.addItem(finalDateList, monthChangeStatus)
            scrollToPos = when (monthChangeStatus) {
                0 -> finalDateList.size - 1
                else -> 0

            }
        }
        linearLayoutManager.scrollToPositionWithOffset(scrollToPos, 0)

    }


    fun build() {
        initCalendar()
    }


    companion object {
        const val MONTH_CHANGE_PREVIOUS = 0
        const val MONTH_CHANGE_CURRENT = 1
        const val MONTH_CHANGE_NEXT = 2

    }


}
