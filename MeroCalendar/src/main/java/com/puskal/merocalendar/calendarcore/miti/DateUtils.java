package com.puskal.merocalendar.calendarcore.miti;

import java.util.Calendar;
import java.util.HashMap;

/**
 * Nepali date conversion utilities and database.
 */
public class DateUtils {
    // Name of the month
    public final static String HEADER_SUN = "S";
    public final static String HEADER_MON = "M";
    public final static String HEADER_TUE = "T";
    public final static String HEADER_WED = "W";
    public final static String HEADER_THU = "T";
    public final static String HEADER_FRI = "F";
    public final static String HEADER_SAT = "S";
    public final static String[] MONTH_NAMES = {"", "Baisakh", "Jestha", "Ashar", "Shrawan", "Bhadra", "Ashoj", "Kartik", "Mangshir", "Poush", "Magh", "Falgun", "Chaitra"};
    public final static String[] MONTH_NAMES_MAPPED = {"", "Apr / May", "May / Jun", "Jun / Jul", "Jul / Aug", "Aug / Sep", "Sep / Oct", "Oct / Nov", "Nov / Dec", "Dec / Jan", "Jan / Feb", "Feb / Mar", "Mar / Apr"};
    public final static String[] MONTH_NAMES_NEP = {"", "बैशाख", "जेष्ठ", "आषाढ", "श्रावण", "भाद्र", "आश्विन", "कार्तिक", "मंसिर", "पौष", "माघ", "फाल्गुन", "चैत्र"};
    public final static String[] NUMBER_NEP = {"०", "१", "२", "३", "४", "५", "६", "७", "८", "९"};
    public final static HashMap<String, Integer> NUMBER_ENG = new HashMap<String, Integer>() {{
        put("०", 0);
        put("१", 0);
        put("२", 0);
        put("३", 0);
        put("४", 0);
        put("५", 0);
        put("६", 0);
        put("७", 0);
        put("८", 0);
        put("९", 0);
    }};
    public final static String[] MONTH_NAMES_AD = {"", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    public final static String[] WEEK_DAY_NAMES = {"", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    // Starting Nepali year that this database starts storing date from.
    public final static int startNepaliYear = 2000;
    // Starting English year that this database starts storing date from.
    public final static Date startEnglishDate = new Date(1943, 4, 14);

    /**
     * Date Database useful for converting from/to Nepali/English dates.
     * <p>
     * Basically, this is an array of arrays. Each sub-array represents a year.
     * Each year contains number of days in each month as array of integers.
     */
    public final static int[][] data = new int[][]{
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 32, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{30, 32, 31, 32, 31, 31, 29, 30, 29, 30, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 29, 31},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 29, 30, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 30, 29, 31},
            new int[]{31, 31, 31, 32, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 29, 30, 29, 30, 30},
            new int[]{31, 32, 31, 32, 31, 30, 30, 30, 29, 29, 30, 30},
            new int[]{31, 31, 32, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{31, 31, 32, 31, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{31, 32, 31, 32, 30, 31, 30, 30, 29, 30, 30, 30},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{31, 31, 32, 31, 31, 31, 30, 30, 29, 30, 30, 30},
            new int[]{30, 31, 32, 32, 30, 31, 30, 30, 29, 30, 30, 30},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
            new int[]{30, 32, 31, 32, 31, 30, 30, 30, 29, 30, 30, 30},
    };

    // Starting Nepali year that this database storing date till.
    public final static int endNepaliYear = startNepaliYear + data.length;

    /**
     * Get {@return number of days} in given {@param year} and {@param month}.
     */
    public static int getNumDays(int year, int month) {
        return data[year - startNepaliYear][month - 1];
    }

    /**
     * Get {@return number of years} that this database stores.
     */
    public static int getNumYears() {
        return data.length;
    }

    /**
     * Convert English date to Nepali date.
     *
     * @param engDate English date to convert from.
     * @return Corresponding Nepali date.
     */
    public static Date getNepaliDate(Date engDate) {
        int days = startEnglishDate.getDaysTill(engDate) + 1;

        for (int i = 0; i < getNumYears(); ++i) {

            for (int j = 0; j < 12; ++j) {
                if (days > data[i][j])
                    days -= data[i][j];
                else
                    return new Date(i + startNepaliYear, j + 1, days);
            }
        }
        return null;
    }

    /**
     * Convert Nepali date to English date.
     *
     * @param nepDate Nepali date to convert from.
     * @return Corresponding English date.
     */
    public static Date getEnglishDate(Date nepDate) {
        int days = 0;
        int year = nepDate.year - startNepaliYear;

        for (int i = 0; i <= year; ++i) {

            if (i >= data.length) break;

            for (int j = 0; j < 12; ++j) {
                if (i == year && j == nepDate.month - 1) {
                    days += nepDate.day - 1;

                    Calendar c = startEnglishDate.getCalendar();
                    c.add(Calendar.DATE, days);
                    return new Date(c);
                } else
                    days += data[i][j];
            }
        }
        return null;
    }

    public static String getDayName(int dayOfWeek) {
        if (dayOfWeek < 1 || dayOfWeek > 7) {
            return "";
        }
        return WEEK_DAY_NAMES[dayOfWeek];
    }

    public static String getMonthName(int monthNumber) {
        return (monthNumber < 1 || monthNumber > 12)
                ? MONTH_NAMES_NEP[0]
                : MONTH_NAMES_NEP[monthNumber];
    }

    public static String getMonthNameAd(int monthNumber) {

        return (monthNumber < 1 || monthNumber > 12)
                ? MONTH_NAMES_AD[0]
                : MONTH_NAMES_AD[monthNumber];
    }

    public static int getMonthNumber(String monthName) {
        int result = 0;
        for (int i = 0; i < MONTH_NAMES_NEP.length; i++) {
            if (MONTH_NAMES_NEP[i].equals(monthName)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static int getMonthNumberAd(String monthName) {
        int result = 0;
        for (int i = 0; i < MONTH_NAMES_AD.length; i++) {
            if (MONTH_NAMES_AD[i].equals(monthName)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public static String getNextMonthName(String monthName) {
        int month = getMonthNumber(monthName) + 1;
        if (month > 12) {
            month = 1;
        }
        return getMonthName(month);
    }

    public static String getNextAdMonthName(String monthName) {
        int month = getMonthNumberAd(monthName) + 1;
        if (month > 12) {
            month = 1;
        }
        return getMonthNameAd(month);
    }

    /**
     * Provides name of the next month in BS.
     *
     * @param monthName
     * @return
     */
    public static String getPreviousMonthName(String monthName) {
        int month = getMonthNumber(monthName) - 1;
        if (month < 1) {
            month = 12;
        }
        return getMonthName(month);
    }

    public static String getPreviousAdMonthName(String monthName) {
        int month = getMonthNumberAd(monthName) - 1;
        if (month < 1) {
            month = 12;
        }
        return getMonthNameAd(month);
    }

    public static Date resetToFirstDayOfBs(Date date) {
        // this is the first day in ad or bs
        date.day = 1;
        return date.convertToEnglish().convertToNepali();
    }

}