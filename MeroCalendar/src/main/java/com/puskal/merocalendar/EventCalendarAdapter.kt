package com.puskal.merocalendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.puskal.merocalendar.databinding.ItemEnglishCalendarBinding
import com.puskal.merocalendar.model.DateModel

/**created by Puskal
 * 30 May, 2021
 */
public class EventCalendarAdapter(val dateClickListener: DateClickListener? = null) :
    RecyclerView.Adapter<EventCalendarAdapter.VH>() {
    private var dateList = arrayListOf<DateModel>()
    var currentSelectionPos = -1
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val binding: ItemEnglishCalendarBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.item_english_calendar,
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

    fun addItem(list: List<DateModel>) {
        dateList.clear()
        dateList.addAll(list)
        currentSelectionPos=-1
        notifyDataSetChanged()
    }


    inner class VH(val binding: ItemEnglishCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DateModel, dateClickListener: DateClickListener?) {
            with(binding) {
                tv.text = item.displayedDayInCalendar

                if (adapterPosition in 0..6) {
                    tv.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.primary_color
                        )
                    )
                } else {
                    tv.setTextColor(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.light_primary_color
                        )
                    )
                }




                if (item.hasEvent) {
                    tv.apply {
                        setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                        background =
                            ContextCompat.getDrawable(root.context, R.drawable.rounded_button)

                    }
                } else {
                    tv.background = null
                }

                if (adapterPosition.plus(1) % 7 == 0) {
                  tv.setTextColor(ContextCompat.getColor(binding.root.context,R.color.holiday_sat_color))
                }

                with(binding) {
                    clDate.setOnClickListener {

                        if (adapterPosition>6 && currentSelectionPos != adapterPosition) {
                            dateClickListener?.let {
                                dateClickListener!!.onDateClick(item)
                            }
                            currentSelectionPos = adapterPosition
                            notifyDataSetChanged()
                        }

                    }

                    if (currentSelectionPos == adapterPosition) {
                        binding.tv.foreground = ContextCompat.getDrawable(root.context, R.drawable.selected_date_bg)
                    } else {
                        binding.tv.foreground = null
                    }

                }


            }
        }
    }
}