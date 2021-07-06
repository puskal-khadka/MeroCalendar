package com.puskal.merocalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.puskal.merocalendar.databinding.LayoutCalendarWithEventBinding
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.EventModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

/**@author Puskal Khadka
 * 3 july, 2021
 */
class MeroCalendarView : LinearLayout {
    private var calendarType: CalendarType = CalendarType.AD
    private var language: LocalizationType = LocalizationType.ENGLISH_US
    private lateinit var binding: LayoutCalendarWithEventBinding
    private var eventList: ArrayList<EventModel> = arrayListOf()
    private var monthChangeListener: MonthChangeListener? = null
    private var dateClickListener: DateClickListener? = null


    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadUi(context, attrs)
    }


    private fun loadUi(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = DataBindingUtil.inflate(inflater, R.layout.layout_calendar_with_event, this, true)
    }


    fun setCalendarType(type: CalendarType) {
        this.calendarType = type
    }

    fun setLanguage(lan: LocalizationType) {
        this.language = lan
    }


    private val calAdapter: EventCalendarAdapter by lazy {
        EventCalendarAdapter(dateClickListener)
    }

    fun initCalendar() {
        val calendar = Calendar.getInstance()
        var currentMonth = calendar.get(Calendar.MONTH)
        var currentYear = calendar.get(Calendar.YEAR)

        setAdapter(currentMonth, currentYear)
        binding.rvCalendar.apply {
            adapter = calAdapter
        }


        binding.ivArrowLeft.setOnClickListener {
            if (currentMonth == 0) {
                currentMonth = 11
                currentYear -= 1
            } else {
                currentMonth -= 1
            }
            setAdapter(currentMonth, currentYear, true)
        }

        binding.ivArrowRight.setOnClickListener {
            if (currentMonth == 11) {
                currentMonth = 0
                currentYear += 1
            } else {
                currentMonth += 1
            }
            setAdapter(currentMonth, currentYear, true)

        }

        with(binding) {
            tvToday.setOnClickListener {
                setAdapter(calendar.get(Calendar.MONTH), calendar.get(Calendar.YEAR), true)
            }
        }
    }

    private fun setAdapter(currentMonth: Int, currentYear: Int, isMonthChange: Boolean = false) {
        GlobalScope.launch(Dispatchers.IO) {
            val (dateList, title) = CalendarController.getDateList(
                calendarType,
                language,
                currentMonth,
                currentYear
            )

            withContext(Dispatchers.Main) {
                calAdapter.addItem(dateList)
                binding.tvDate.text = title
                if (isMonthChange) {
                    monthChangeListener?.onMonthChange(currentYear, currentMonth)
                }
            }


        }
    }

    fun setEvent(myEventList: ArrayList<EventModel>) {

        GlobalScope.launch(Dispatchers.IO) {

            eventList.clear()
            eventList.addAll(myEventList)

            val currentMonthDateList = CalendarController.currentMonthDateList

            for (event in eventList) {
                val fromDate = event.FromDate.substringBefore("T").split("-")
                if (fromDate.size != 3) {
                    continue
                }
                val from_y = fromDate[0].toInt()
                val from_m = fromDate[1].toInt()
                val from_d = fromDate[2].toInt()

                val toDate = event.toDate.substringBefore("T").split("-")
                if (toDate.size != 3) {
                    continue
                }
                val to_y = toDate[0].toInt()
                val to_m = toDate[1].toInt()
                val to_d = toDate[2].toInt()


                for (dateModel in currentMonthDateList) {
                    val date = dateModel.formattedAdDate.split("-")
                    if (date.size != 3) {
                        continue
                    }
                    val date_y = date[0].toInt()
                    val date_m = date[1].toInt()
                    val date_d = date[2].toInt()

                    if (date_y in from_y..to_y && date_m in from_m..to_m && date_d in from_d..to_d) {
                        dateModel.hasEvent = true
                        dateModel.eventColorCode = event.colorCode
                        dateModel.isHoliday = event.isHolidayEvent
                    }

                }

            }

            with(Dispatchers.Main) {
                calAdapter.addItem(currentMonthDateList)

            }

        }
    }


    fun setUpMonthChangeListener(listener: MonthChangeListener) {
        monthChangeListener = listener
    }

    fun setOnDateClickListener(listener: DateClickListener) {
        dateClickListener = listener
    }




}
