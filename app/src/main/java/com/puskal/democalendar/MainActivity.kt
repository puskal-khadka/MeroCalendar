package com.puskal.democalendar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.puskal.merocalendar.EventCalendarFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       supportFragmentManager.beginTransaction().replace(R.id.flFragment, EventCalendarFragment()).commit()
    }
}