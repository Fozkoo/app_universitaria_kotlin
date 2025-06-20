package com.example.app_infounsada.network

import com.example.app_infounsada.data.ModuleDTO
import com.example.app_infounsada.data.CalendarEventDTO
import retrofit2.http.GET

interface APIService {
    @GET("api/module/getAllModule")
    suspend fun getAllModules(): List<ModuleDTO>

    @GET("api/calendar/getAll")
    suspend fun getAllCalendarEvents(): List<CalendarEventDTO>



}
