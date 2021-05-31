package com.puskal.merocalendar.calendarcore
import com.puskal.merocalendar.calendarcore.miti.Date

interface OnDateChangedCallback {
    fun onDateChanged(date: Date?)
}