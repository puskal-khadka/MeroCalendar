package com.puskal.merocalendar.model


data class EventModel(
    val event:String ="",  //eg: Holiday Event, Payroll Event
    val eventType:String = "", //eg: Festivals,Exam
    val eventName:String="", //eg: New Year
    val description:String="",//eg:New year 2021, Public Holiday
    val FromDate:String="",  //date should be AD, format: 2021-07-05T00:00:00
    val toDate:String ="", //  same as above fromDate format
    val colorCode: String = "#ff0000", //event color code
    val isHolidayEvent:Boolean=false

)
