package com.example.app_infounsada

import retrofit2.http.GET

interface APIService {
    @GET
    fun getAllModulesByID()
}