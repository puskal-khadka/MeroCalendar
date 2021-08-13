package com.puskal.merocalendar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.puskal.merocalendar.HorizontalMeroCalendarView.Companion.MONTH_CHANGE_CURRENT
import com.puskal.merocalendar.HorizontalMeroCalendarView.Companion.MONTH_CHANGE_NEXT
import com.puskal.merocalendar.HorizontalMeroCalendarView.Companion.MONTH_CHANGE_PREVIOUS
import com.puskal.merocalendar.calendarcore.LocalizationHelper
import com.puskal.merocalendar.databinding.ItemHorizontalCalendarDateBinding
import com.puskal.merocalendar.enum.LocalizationType
import com.puskal.merocalendar.model.DateModel

/**created by Puskal
 * 8 july, 2021
 */
public class HorizontalCalendarAdapter(val dateClickListener: DateClickListener? = null) :
    RecyclerView.Adapter<HorizontalCalendarAdapter.VH>() {
    private var dateList = arrayListOf<DateModel>()
    var currentSelectionPos = -1
    var monthChangeStatus = MONTH_CHANGE_CURRENT
    var localizationType:LocalizationType=LocalizationType.ENGLISH_US
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: ItemHorizontalCalendarDateBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_horizontal_calendar_date,
            parent,
            false
        )

        return VH(binding)
    }

    override fun getItemCount(): Int {
        return dateList.size
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(dateList[position], dateClickListener)
    }

    fun addItem(list: List<DateModel>, monthChangeStatus: Int,localizationType:LocalizationType, currentDayIndex: Int =-1) {
        dateList.clear()
        dateList.addAll(list)
        currentSelectionPos = currentDayIndex  //useful for current month
        this.monthChangeStatus = monthChangeStatus
        this.localizationType=localizationType

        when (monthChangeStatus) {
            MONTH_CHANGE_CURRENT -> {

            }

            MONTH_CHANGE_NEXT -> {
                currentSelectionPos=0
            }
            MONTH_CHANGE_PREVIOUS -> {
             currentSelectionPos=dateList.size-1
            }
        }

        notifyDataSetChanged()
    }


    inner class VH(val binding: ItemHorizontalCalendarDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DateModel, dateClickListener: DateClickListener?) {
            with(binding) {
                tvDate.text = item.displayedDayInCalendar

                if (item.todayWeekDay != 0) {
                    val weekDay = item.todayWeekDay.minus(1) % 7
                    tvDay.text = when (localizationType) {
                        LocalizationType.ENGLISH_US -> {
                            LocalizationHelper.weekNameInEnglish(weekDay.plus(1))
                        }
                        LocalizationType.NEPALI_NP -> {
                            LocalizationHelper.weekNameInNepali(weekDay.plus(1))
                        }
                    }
                } else {
                    tvDay.text = ""
                }



                clDate.setOnClickListener {
                    if (currentSelectionPos != adapterPosition) {
                        currentSelectionPos = adapterPosition
                        notifyDataSetChanged()
                    }

                }

                var colorId=ContextCompat.getColor(binding.root.context,R.color.mero_accent_color)

                if (adapterPosition == currentSelectionPos) {
                    tvDay.setTextColor(colorId)
                    tvDate.setTextColor(colorId)
                    viewSelected.visibility = View.VISIBLE
                    dateClickListener?.let {
                        dateClickListener.onDateClick(item)
                    }
                }
                else{
                    tvDay.setTextColor(ContextCompat.getColor(binding.root.context,R.color.mero_primary_color))
                    tvDate.setTextColor(ContextCompat.getColor(binding.root.context,R.color.mero_primary_color))
                    viewSelected.visibility=View.GONE
                }


            }
        }


    }
}