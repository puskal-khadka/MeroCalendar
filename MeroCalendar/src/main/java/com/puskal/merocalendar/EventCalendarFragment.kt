package com.puskal.merocalendar

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.puskal.merocalendar.calendarcore.miti.DateUtils
import com.puskal.merocalendar.databinding.EnglishEventCalendarFragmentBinding
import com.puskal.merocalendar.model.DateModel
import dynamic.school.ui.eventcalendar.EventCalendarViewModel
import java.text.SimpleDateFormat
import java.util.*
import com.puskal.merocalendar.calendarcore.miti.Date

/**created by Puskal khadka
 * 29 may , 2021
 */
class EventCalendarFragment : Fragment() {

    companion object {
        fun newInstance() = EventCalendarFragment()
        const val TAG="mero_calendar"
    }

    private lateinit var viewModel: EventCalendarViewModel
    private lateinit var binding: EnglishEventCalendarFragmentBinding
    private lateinit var calendarAdapter: EventCalendarAdapter

    private var month: Int = 0
    private var year: Int = 0
    private lateinit var calendar: Calendar


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.english_event_calendar_fragment,
            container,
            false
        )

        calendarAdapter = EventCalendarAdapter()
        binding.rvCalendar.adapter = calendarAdapter




        var alert: AlertDialog? = null

        val dialogBuilder = AlertDialog.Builder(requireContext())
        var option = arrayOf("English Calendar", "Nepali Calendar")
        dialogBuilder.apply {
            setCancelable(false)
            setTitle("Calendar . Testing -> select one -->Puskal")
            setSingleChoiceItems(option, -1) { dialog, which ->
                when (which) {
                    0 -> {
                        englishCalendarController()
                    }
                    1 -> {
                        nepaliCalendarController()
                    }
                }
                alert?.dismiss()
            }

        }
        alert = dialogBuilder.create()
        alert.show()




        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(EventCalendarViewModel::class.java)
        // TODO: Use the ViewModel
    }

    /**puskal khadka
     * Logic for custom calendar
     */

    public val daysInMonth = arrayOf<Int>(31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
    val weekendDay = arrayOf<String>("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
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

    fun englishCalendar() {

        Log.d("meroCalendar","show $year  $month")
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        Log.d("mero","liek me d${monthName[calendar.get(Calendar.MONTH)]}, ${calendar.get(Calendar.YEAR)}")

        binding.tvDate.text =
            "${monthName[calendar.get(Calendar.MONTH)]}, ${calendar.get(Calendar.YEAR)}"

        val dateList = arrayListOf<DateModel>(
            DateModel("sun"),
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

        Log.d(TAG,"month is $month  and year is $year  and total  days in this month $daysInThisMonth  and first week day of month $weekNumberOfFirstDayOfMonth ")

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

        calendarAdapter.addItem(dateList)


    }

    fun nepaliCalendarController() {
        val todayNepaliDate = DateUtils.getNepaliDate(Date(Calendar.getInstance()))
        Log.d("mero","today nepali date is $todayNepaliDate")
        month=todayNepaliDate.month
        year=todayNepaliDate.year
        nepaliCalendar()
        with(binding) {
            ivArrowLeft.setOnClickListener {
                if (month == 1) {
                    month = 12
                    year = year - 1
                } else {
                    month--
                }
                nepaliCalendar()
            }

            ivArrowRight.setOnClickListener {
                if (month == 12) {
                    month = 1
                    year = year + 1
                } else
                    month++
                nepaliCalendar()
            }
        }
    }

    fun nepaliCalendar() {


        val dateList = arrayListOf<DateModel>(
            DateModel("sun"),
            DateModel("Mon"),
            DateModel("Tue"),
            DateModel("Wed"),
            DateModel("Thu"),
            DateModel("Fri"),
            DateModel("Sat")
        )
        calendarAdapter.addItem(dateList)

        val todayNepaliDate = DateUtils.getNepaliDate(Date(Calendar.getInstance()))

          val nepaliDate=Date(year,month,1)

        binding.tvDate.text = "${DateUtils.getMonthName(nepaliDate.month)}  ${nepaliDate.year}"


        val nepaliFirstMonthDayInEnglish =
            DateUtils.getEnglishDate(Date(nepaliDate.year, nepaliDate.month, 1))
        Log.d(TAG,"nepali first in eglis $nepaliFirstMonthDayInEnglish")

        val numberOfDaysInMonth = DateUtils.getNumDays(nepaliDate.year, nepaliDate.month)
        Log.d(TAG,"total number of days in this month $numberOfDaysInMonth")


        val weekNumberOfFirstDayOfNepaliMonth = nepaliFirstMonthDayInEnglish.weekDayNum
        Log.d(TAG,"number of week in this month $weekNumberOfFirstDayOfNepaliMonth")

        for (i in 1 until weekNumberOfFirstDayOfNepaliMonth) {
            dateList.add(DateModel(""))
        }

        for (i in 1..numberOfDaysInMonth) {
            nepaliDate.day = i
            val convertedAdDate = DateUtils.getEnglishDate(nepaliDate)
            val formattedEnglishDate="${convertedAdDate.year}-${convertedAdDate.month}-${convertedAdDate.day}"
            val adDate=SimpleDateFormat("yyyy-MM-dd").parse(formattedEnglishDate)
            val dateModel = DateModel(
                i.toString(),
                formattedAdDate = formattedEnglishDate,
                formattedBsDate = "${nepaliDate.year}-${nepaliDate.month}-${nepaliDate.day}",
                adDate =  adDate
            )

            if (todayNepaliDate.year == year && todayNepaliDate.month == month && todayNepaliDate.day == i) {
                dateModel.hasEvent=true
                dateModel.eventColor = R.color.accent_color

            }


            dateList.add(dateModel)
            Log.d(TAG,"dateModel is  $i adn  ${dateModel.formattedAdDate} and ${dateModel.formattedBsDate}")


        }
        calendarAdapter.addItem(dateList)



    }



}