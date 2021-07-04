package com.puskal.merocalendar

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import com.puskal.merocalendar.databinding.LayoutCalendarWithEventBinding
import com.puskal.merocalendar.enum.CalendarType
import com.puskal.merocalendar.enum.LocalizationType
import java.util.*

/**@author Puskal Khadka
 * 3 july, 2021
 */
class MeroCalendarView : LinearLayout {
    private var calendarType: CalendarType =CalendarType.AD
    private var language: LocalizationType = LocalizationType.ENGLISH_US
    private lateinit var binding: LayoutCalendarWithEventBinding

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        loadUi(context, attrs)
    }


    private fun loadUi(context: Context, attrs: AttributeSet?) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
         binding=DataBindingUtil.inflate(inflater,R.layout.layout_calendar_with_event,this,true)
    }


    fun setCalendarType(type:CalendarType){
        this.calendarType=type
    }

    fun setLanguage(lan: LocalizationType) {
        this.language=lan
    }


    private val calAdapter:EventCalendarAdapter by lazy {
        EventCalendarAdapter()
    }
     fun initCalendar() {
         val calendar=Calendar.getInstance()
         var currentMonth = calendar.get(Calendar.MONTH)
         var currentYear=calendar.get(Calendar.YEAR)

         setAdapter(currentMonth,currentYear)
        binding.rvCalendar.apply {
           adapter= calAdapter
        }


         binding.ivArrowLeft.setOnClickListener {
             if (currentMonth == 0) {
                 currentMonth=11
                 currentYear -= 1
             }
             else {
                 currentMonth-=1
             }
             setAdapter(currentMonth,currentYear)
         }

         binding.ivArrowRight.setOnClickListener {
             if (currentMonth == 11) {
                 currentMonth=0
                 currentYear+=1
             }
             else{
                 currentMonth+=1
             }
             setAdapter(currentMonth,currentYear)
         }
    }

    private fun setAdapter(currentMonth: Int, currentYear: Int) {
        val (dateList,title)=CalendarController.getDateList(calendarType,language,currentMonth,currentYear)
        calAdapter.addItem(dateList)
        binding.tvDate.text=title
    }



}