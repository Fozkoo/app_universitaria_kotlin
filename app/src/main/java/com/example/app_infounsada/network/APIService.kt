package com.example.app_infounsada.network

import com.example.app_infounsada.data.ModuleDTO
import com.example.app_infounsada.data.CalendarEventDTO
import com.example.app_infounsada.data.TopicDTO
import com.example.app_infounsada.data.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Path

interface APIService {
    @GET("api/modules")
    suspend fun getAllModules(): List<ModuleDTO>

    @GET("api/calendar/getAll")
    suspend fun getAllCalendarEvents(): List<CalendarEventDTO>

    @GET("api/topics")
    suspend fun getAllTopics(): List<TopicDTO>

    @GET("api/modules/topic-name/{name}")
    suspend fun getModulesByTopic(@Path("name") topicName: String): List<ModuleDTO>

    @GET("api/news")
    suspend fun getAllNews(): List<NewsDTO>
}
