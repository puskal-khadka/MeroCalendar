package com.puskal.democalendar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.puskal.democalendar.databinding.FragmentDateConversionBinding
import com.puskal.merocalendar.calendarcore.MeroDateConverter
import java.util.*

class DateConversionFragment : Fragment() {

    private lateinit var binding: FragmentDateConversionBinding
    var isAdToBs = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_date_conversion, container, false)
        with(binding) {
            rgConfig.setOnCheckedChangeListener { group, checkedId ->
                isAdToBs = checkedId == R.id.rbFromAd
                btnConvert.text =
                    if (checkedId == R.id.rbFromAd) "Convert To BS" else "Convert To AD"
            }

            btnConvert.setOnClickListener {

                val year = (etYear.text.toString())
                val month = etMonth.text.toString()
                val day = etDay.text.toString()


                if (year.isEmpty() || month.isEmpty() || day.isEmpty())
                    Toast.makeText(
                        requireContext(),
                        "Year, month & day should be valid & non empty",
                        Toast.LENGTH_SHORT
                    ).show()
                else {
                    var result= if(isAdToBs) MeroDateConverter.convertAdToBs(year.toInt(), month.toInt(), day.toInt()) else MeroDateConverter.convertBsToAd(year.toInt(), month.toInt(), day.toInt())
                    val localizedResult=if(isAdToBs) MeroDateConverter.convertAdToBs(year.toInt(), month.toInt(), day.toInt(),showInEnglishLanguage = false) else MeroDateConverter.convertBsToAd(year.toInt(), month.toInt(), day.toInt(),showInEnglishLanguage = false)
                    if (result.isEmpty()) {
                        Toast.makeText(
                            requireContext(),
                            "Sorry, Enter Date is Out of Range",
                            Toast.LENGTH_SHORT
                        ).show()                    }

                    tvResult.text = result
                    tvLocalizedResult.text=localizedResult
                }


            }
        }


        return binding.root
    }


}