package com.puskal.merocalendar

import com.puskal.merocalendar.model.DateModel

interface MonthChangeListener {
    fun onMonthChange(startDateOfThisMonth:DateModel,endDateOfThisMonth:DateModel,adYear:Int, adMonth: Int)
}