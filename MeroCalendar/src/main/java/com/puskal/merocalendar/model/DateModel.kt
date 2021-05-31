package com.puskal.merocalendar.model

import java.util.*


/**Created by puskal khadka
 * 29 may, 2021
 */
class DateModel(
    val displayedDayInCalendar: String,
    val adDate: Date = Date(),
    val formattedAdDate:String="",   //2021-04-29
    val formattedBsDate:String="",  //2077-01-16
    val isAd:Boolean=true,
    var hasEvent: Boolean = false,
    var eventColor: Int = -1,
    val isHoliday:Boolean=false,
    val isToday: Boolean = false
    )