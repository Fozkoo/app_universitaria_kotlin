package com.example.app_infounsada.network

import com.example.app_infounsada.data.ModuleDTO
import com.example.app_infounsada.data.CalendarEventDTO
import com.example.app_infounsada.data.TopicDTO
import com.example.app_infounsada.data.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {
    @GET("api/module/getAllModule")
    suspend fun getAllModules(): List<ModuleDTO>

    @GET("api/calendar/getAll")
    suspend fun getAllCalendarEvents(): List<CalendarEventDTO>

    @GET("api/topic/getAllTopic")
    suspend fun getAllTopics(): List<TopicDTO>

    @GET("api/module/getByTopic")
    suspend fun getModulesByTopic(@Query("name") topicName: String): List<ModuleDTO>

    @GET("api/news/getAllNews")
    suspend fun getAllNews(): List<NewsDTO>
}
