# MeroCalendar 
Android Calendar Library With AD and Bikram Sambat event calendar, Converter + Both english and nepali language support

[![](https://jitpack.io/v/puskal-khadka/MeroCalendar.svg)](https://jitpack.io/#puskal-khadka/MeroCalendar)
[![License](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://github.com/puskal-khadka/MeroCalendar/blob/master/LICENSE)

<img src="demo assets/merocalendar_bs_np.jpg" width="200" height="435"/> &nbsp; <img src="demo assets/merocalendar_ad_en.jpg"  width="200" height="435"/> &nbsp;
<img src="demo assets/merocalendar_converter.gif" width="202" height="435"/> &nbsp; <img src="demo assets/merocalendar_horizontalCalendar.gif"  width="200" height="435"/> 






# Features
- AD, BS Calendar (with or without event)
- Date Conversion from AD to BS and vice versa
- Horizontal Calendar (Both AD , BS)
- Highly Customized
- Nepali And English language Full support

# Installation
Add jitack repo on your project `build.gradle`
```groovy
   allprojects {
      repositories {
          google()
          jcenter()
          maven { url "https://jitpack.io" }
      }
   }
```
Then add MeroCalendar Library to your app `build.gradle`
```groovy
   dependencies {
	        implementation 'com.github.puskal-khadka:MeroCalendar:1.0.2'
	}
```
<br />

# Using Event Calendar (For AD or BS)
- **Add MeroCalendarView to your layout file**
```xml
   <com.puskal.merocalendar.MeroCalendarView
    android:id="@+id/mCalendarView"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    android:layout_margin="8dp"
   />
```
- **Then in your activity or Fragment add Configuration as your requirement as below**
- 1.1 Setup DateClick Listener

```kotlin
   val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.d("d", "clicked date is ${dateModel.formattedAdDate}")
            }
        }
```
- 1.2 Setup MonthChange Listener
```kotlin
   val monthChangeListener = object : MonthChangeListener {
            override fun onMonthChange( startDateOfThisMonth: DateModel, endDateOfThisMonth: DateModel, adYear: Int, adMonth: Int) {
            //perform action or set event of corresponding month from here
          }
        }   
 ```
 - 1.3 Set Calendar Type
```kotlin
   val calendarType=CalendarType.BS 
   //for Ad calendar use,  val calendarType=CalendarType.AD
```
   
 - 1.4 Set Language
```kotlin
   val language=LocalizationType.NEPALI_NP
   //for english language use, val language=LocalizationType.ENGLISH_US
```
 

- 1.5 Finally Build calendar using above configuraion as follow
```kotlin 
   mCalendarView.setCalendarType(calendarType)
            .setLanguage(language)
            .setOnDateClickListener(dateClickListener)
            .setOnMonthChangeListener(monthChangeListener)
            .setEvent(fakeEventList)
            .build()
 ```
 
<br />
<br />
 
# Using Date Converter (AD to BS and vice versa)
 
**To convert AD to BS**
- Get (AD) year, month, day and pass to the converter of the library as given below
```kotlin  
   val year:Int=2021
   val month=7  //month number should be from 1 to 12 (eg: 1=jan, 12=dec)
   val day=12
   val convertedAdDate=MeroDateConverter.convertAdToBs(year, month, day)
```
 
**To convert BS to Ad**
- Get (nepali/BS) year, month, day and pass to the converter of the library as below
```kotlin
   val year=2078
   val month=3 //from 1 to 12 (eg: 1=baishakh, 12=chaitra_
   val day=28
   val convertedBsDate=MeroDateConverter.convertBsToAd(year, month, day)
 ```
 
<br />
<br />


# Using Horizontal Calendar
- **Add MeroHorizontalCalendarView on xml layout**
```xml
   <com.puskal.merocalendar.HorizontalMeroCalendarView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:id="@+id/horizontalCalendarView"
    />

```
- **After that in your activity or fragment, add configuration**

- Set up Date click listener to observe clicked date
```kotlin 
   val dateClickListener = object : DateClickListener {
            override fun onDateClick(dateModel: DateModel) {
                Log.d("d","data is ${dateModel.localizedFormattedDate}")
            }
        }
```
- build horizontal calendar
```kotlin
   var calendarType = CalendarType.AD  //for bs use, CalendarType.BS
   var language = LocalizationType.ENGLISH_US //for nepali language use, LocalizationType.ENGLISH_US
   binding.horizontalCalendarView.setCalendarType(calendarType)
                    .setLanguage(language)
                    .setOnDateClickListener(dateClickListener)
                    .build()
```
- **Additional Things on Horizontal Calendar View**
- To get previous and next month horizontal calendar, if ivNext is view for getting next month, then on clicking this button perform following action
```kotlin
   ivNext.setOnClickListener {
                horizontalCalendarView.setNextMonthDate()
            }
```
- Similarly on clicking previous button, perform following action
```kotlin
   ivPrevious.setOnClickListener {
                horizontalCalendarView.setPreviousMonthDate()
            }
```
 
 <br />
 
## Contribution
- Feel free to submit pull request. Also if you have any issue (on latest version) or want some other features on this library, kindly open an issue.

## Credit
- Miti 
 
 
 
 ## License
 [http://www.apache.org/licenses/LICENSE-2.0](http://www.apache.org/licenses/LICENSE-2.0)

    Copyright 2021 MeroCalendar Author
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

  
 

