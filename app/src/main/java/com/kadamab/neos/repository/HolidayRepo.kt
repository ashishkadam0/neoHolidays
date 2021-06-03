package com.kadamab.neos.repository

import Holiday
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kadamab.neos.requestParam.RequestParam
import com.kadamab.seersorders.Retrofit.InterOrder
import com.kadamab.seersorders.Retrofit.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HolidayRepo : ViewModel() {
    private val model: MutableLiveData<List<Holiday>> = MutableLiveData()

    fun observeHolidayData(): MutableLiveData<List<Holiday>> {
        return model
    }

    fun requestholidayData(country: String, year: String) {

        val holidayDataCall: Call<List<Holiday>> = myInterface.getHoliday()
        //val holidayDataCall: Call<List<Holiday>> = myInterface.getHoliday(RequestParam.ACTION, year,country, RequestParam.TYPE)
        holidayDataCall.enqueue(object : Callback<List<Holiday>?> {
            override fun onResponse(call: Call<List<Holiday>?>, response: Response<List<Holiday>?>) {
                Log.i("&&RESPONSE",response.body().toString())
                model.setValue(response.body())
            }

            override fun onFailure(call: Call<List<Holiday>?>, t: Throwable) {
                Log.i("&&ERRRRR",t.localizedMessage)
                model.postValue(null)
            }
        })

    }

    companion object {
        private lateinit var myInterface: InterOrder
        private var holidayDataRepository: HolidayRepo? = null
        val instance: HolidayRepo?
            get() {
                if (holidayDataRepository == null) {
                    holidayDataRepository = HolidayRepo()
                }
                return holidayDataRepository
            }
    }

    init {
        myInterface = ServiceBuilder.buildService(InterOrder::class.java)
    }
}