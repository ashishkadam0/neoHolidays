package com.kadamab.neos.view

import Holiday
import android.app.ProgressDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kadamab.neos.adapter.HolidayAdapter
import com.kadamab.neos.databinding.LayoutMainBinding
import com.kadamab.neos.viewModel.HolidayViewModel

@Suppress("DEPRECATION")
class HolidayFragment : Fragment() {
    private lateinit var progressDialog: ProgressDialog
    private var viewModel: HolidayViewModel? = null
    private lateinit var viewManagerOrder: RecyclerView.LayoutManager
    private lateinit var listHolidays: List<Holiday>
    private lateinit var binding: LayoutMainBinding
    private var country = "India"
    private var year = "2020"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progressDialog = ProgressDialog(context)
        progressDialog.setMessage("Please wait..")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = LayoutMainBinding.inflate(inflater)
        initSetup()
        return binding.root
    }

    private fun initSetup() {
        setSpinnerListener();
        viewManagerOrder = LinearLayoutManager(activity)
        val factory = activity?.application?.let {
            ViewModelProvider.AndroidViewModelFactory.getInstance(it)
        }
        viewModel = factory?.let {
            ViewModelProvider(
                requireActivity(),
                it
            ).get(HolidayViewModel::class.java)
        }

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeData()
    }

    private fun observeData() {
        viewModel!!.requestListOrderData(country, year)
        viewModel!!.observeApiData().observe(viewLifecycleOwner, Observer {
            it?.let {
                if (it.isNotEmpty()) {
                    listHolidays = it
                    setUp(binding.holidayRecycler, listHolidays)
                } else {
                    Toast.makeText(
                        context,
                        "Something went wrong , please try again later.",
                        Toast.LENGTH_SHORT
                    ).show()
                    progressDialog.let {
                        if (it.isShowing) it.hide()
                    }
                }
            }
        })
    }

    private fun setUp(hoRecycler: RecyclerView, holiday: List<Holiday>) {
        hoRecycler.setLayoutManager(LinearLayoutManager(context))
        hoRecycler.setAdapter(HolidayAdapter(holiday))
    }

    private fun setSpinnerListener() {
        binding.btnFetch.setOnClickListener {
            viewModel?.requestListOrderData(country, year)
        }
        binding.spinnerc.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                country = parent?.selectedItem.toString() } }

        binding.spinnery.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {}
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                year = parent?.selectedItem.toString()
            }
        }
    }
}
