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
public class EventCalendarAdapter : RecyclerView.Adapter<EventCalendarAdapter.VH>() {
    private var dateList = arrayListOf<DateModel>()
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
        holder.bind(dateList[position])
    }

    fun addItem(list: List<DateModel>) {
        dateList.clear()
        dateList.addAll(list)
        notifyDataSetChanged()
    }


    inner class VH(val binding: ItemEnglishCalendarBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DateModel) {
            with(binding) {
                tv.text = item.displayedDayInCalendar
                if (item.hasEvent) {
                    tv.apply {
                        setTextColor(ContextCompat.getColor(binding.root.context, R.color.white))
                        background = ContextCompat.getDrawable(root.context, R.drawable.rounded_button)
                    }
                } else {
                    tv.background=null
                    tv.setTextColor(ContextCompat.getColor(binding.root.context, R.color.primary_color))
                }

            }
        }
    }
}