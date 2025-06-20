package com.example.app_infounsada.data

data class ModuleDTO(
    val id: Long?,
    val name: String,
    // Ajusta según la respuesta real de tu API
    val topicos: List<TopicoDTO> = emptyList()
)

data class TopicoDTO(
    val id: Long?,
    val name: String
)