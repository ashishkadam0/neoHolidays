package com.kadamab.neos.requestParam

object RequestParam {
    const val ACTION = "getHolidaysForYear"
    const val TYPE = "public_holiday"
    const val BASE_URL = "https://kayaposoft.com/enrico/json/"
    const val YEAR = "yr"
    const val COUNTRY = "ct"
    object RequestMethod {
        const val GET_HOLIDAY = "v2.0/?action=getHolidaysForYear&year=2022&country=est&holidayType=public_holiday"
        //const val GET_HOLIDAY = "v2.0/?"

    }
}