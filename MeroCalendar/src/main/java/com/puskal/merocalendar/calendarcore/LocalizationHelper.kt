package com.puskal.merocalendar.calendarcore

/** @author Puskal Khadka
 * 4 june, 2021
 * To show Date related things in Nepali Devnagarik text
 */
object LocalizationHelper {

    private val nepaliDigit = arrayOf("०", "१", "२", "३", "४", "५", "६", "७", "८", "९")
    private val nepaliMonthArray_inNepaliFont = arrayOf(
        "बैशाख",
        "जेष्ठ",
        "असार",
        "साउन",
        "भदौ",
        "असोज",
        "कार्तिक",
        "मंसिर",
        "पुष",
        "माघ",
        "फागुन",
        "चैत्"
    )
    private val nepaliMonthArray_inEnglishFont = arrayOf(
        "Baisakh",
        "Jestha",
        "Ashar",
        "Shrawan",
        "Bhadra",
        "Ashoj",
        "Kartik",
        "Mangshir",
        "Poush",
        "Magh",
        "Falgun",
        "Chaitra"
    )

    private val weekDay_inEnglish = arrayOf("Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat")
    private val weekDay_inNepali = arrayOf("आईत", "सोम", "मंगल", "बुध", "बिही", "शुक्र", "शनि")

    private val englishMonthArray_inEnglishFont = arrayOf(
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
    private val englishMonthArray_inNepaliFont = arrayOf(
        "जनवरी",
        "फेब्रुअरी",
        "मार्च",
        "अप्रैल",
        "मे",
        "जून",
        "जुलाई",
        "अगस्त",
        "सेप्टेम्बर",
        "अक्टूबर",
        "नोवेम्बर",
        "दिसेम्बर"
    )


    fun toNepaliDigit(str: String?): String {
        var returnString = ""
        str?.let {
            val stringToArray = str.toCharArray()
            for (char in stringToArray) {
                if (char.isDigit()) {
                    val nepDigit = nepaliDigit[char.toString().toInt()]
                    returnString += nepDigit
                } else {
                    returnString += char
                }
            }
        }
        return returnString
    }

    fun nepaliMonthNameInEngFont(monthNum: Int): String {
        var monthName = ""
        if (monthNum in 1..12) {
            monthName = nepaliMonthArray_inEnglishFont[monthNum - 1]
        }
        return monthName
    }


    fun nepaliMonthNameInNepaliFont(monthNum: Int): String {
        var monthName = ""
        if (monthNum in 1..12) {
            monthName = nepaliMonthArray_inNepaliFont[monthNum - 1]
        }
        return monthName
    }

    fun englishMonthNameInNepaliFont(monthNum: Int): String {
        return if (monthNum in 1..12) englishMonthArray_inNepaliFont[monthNum - 1] else ""
    }

    fun englishMonthNameInEnglishFont(monthNum: Int): String {
        return if (monthNum in 1..12)  englishMonthArray_inEnglishFont[monthNum - 1] else ""
    }


}