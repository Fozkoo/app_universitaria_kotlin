package com.example.app_infounsada.data

data class ModuleDTO(
    val idmodule: Long? = null,
    val title: String,
    val content: String,
    val createAt: String,
    val deleteAt: String?,
    val imagePath: String?,
    val topicId: String?,
    val topicName: String

)
