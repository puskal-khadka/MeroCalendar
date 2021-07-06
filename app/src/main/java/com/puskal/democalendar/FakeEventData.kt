package com.puskal.democalendar

import com.puskal.merocalendar.model.EventModel

object FakeEventData {

    val eventList: ArrayList<EventModel> = arrayListOf<EventModel>(
        EventModel("Holiday","Festival","Nation Holiday","Dashain Holiday","2021-07-13T00:00:00","2021-07-16T00:00:00","#00ff00"),
        EventModel("Office","Payroll","Salary Day","Release Salary of Employee","2021-07-05T00:00:00","2021-07-05T00:00:00","#0000ff"),
        EventModel("Office","Promotion ","Promotion Day","Promote Best employee","2021-07-10T00:00:00","2021-07-10T00:00:00","#00ff00"),
        )
}