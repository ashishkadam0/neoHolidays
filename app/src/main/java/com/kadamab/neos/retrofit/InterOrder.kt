package com.kadamab.seersorders.Retrofit

import Holiday
import com.kadamab.neos.requestParam.RequestParam
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query


interface InterOrder {
    @GET(RequestParam.RequestMethod.GET_HOLIDAY)
    fun getHoliday(): Call<List<Holiday>>
   /*fun getHoliday(
        @Query("action") input: String?,
        @Query("year") location: String?,
        @Query("country") key: String?,
        @Query("holidayType") type: String?): Call<List<Holiday>>*/
}

