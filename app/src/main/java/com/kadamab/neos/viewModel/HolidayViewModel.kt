package com.kadamab.neos.viewModel

import Holiday
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.kadamab.neos.repository.HolidayRepo


class HolidayViewModel(application: Application) : AndroidViewModel(application) {

    private val holidayDataRepository: HolidayRepo = HolidayRepo()

    fun requestListOrderData(country: String, year: String) {
        holidayDataRepository.requestholidayData(country, year)
    }

    fun observeApiData(): MutableLiveData<List<Holiday>> {
        return holidayDataRepository.observeHolidayData()
    }

    fun fetchResult(country: String, year: String) {

    }
}
