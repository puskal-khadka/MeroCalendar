package com.puskal.merocalendar.calendarcore

import com.puskal.merocalendar.calendarcore.miti.DateUtils
import java.util.*

object MeroDateConverter {
    val cal = Calendar.getInstance()

    fun getNepaliDate(date: Date) {
        cal.time=date
        DateUtils.getNepaliDate(com.puskal.merocalendar.calendarcore.miti.Date(cal))
    }
    fun getNepaliDate(calendar: Calendar) {
        DateUtils.getNepaliDate(com.puskal.merocalendar.calendarcore.miti.Date(calendar))
    }


}