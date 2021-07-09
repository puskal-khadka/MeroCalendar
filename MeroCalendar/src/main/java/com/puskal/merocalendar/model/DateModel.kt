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
    val todayWeekDay:Int=0,  //value: sunday=1, saturday=7 + also if its value=0, consider invalid date
    var hasEvent: Boolean = false,
    var eventColorCode: String ="",
    var eventName:String="",
    var isHoliday:Boolean=false,
    var isToday: Boolean = false
    )