package com.example.app_infounsada.data

data class CalendarEventDTO(
    val id: Long,
    val title: String,
    val description: String,
    val date: String? // ← Importante que sea nullable
)
