package com.example.app_infounsada.data

data class ModuleDTO(
    val id: Long? = null,
    val title: String,
    val content: String,
    val topic_name: String,
    val image_path: String?,
    val date_create: String,
    val date_delete: String?
)
