package com.puskal.democalendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.puskal.democalendar.databinding.FragmentHomeBinding
import com.puskal.merocalendar.EventCalendarFragment

class HomeFragment : Fragment() {
    private lateinit var binding:FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_home, container, false)

        with(binding) {
            mcvDateConversion.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.flFragment,DateConversionFragment()).addToBackStack(null).commit()
            }

            mcvEnCalendar.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.flFragment,EventCalendarFragment()).addToBackStack(null).commit()
            }

            mcvNpCalendar.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.flFragment,CalFragment()).addToBackStack(null).commit()
            }

            mcvHorizontalCalendar.setOnClickListener {
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.flFragment,HorizontalCalendarFragment()).addToBackStack(null).commit()

            }

        }
        return  binding.root
    }


}