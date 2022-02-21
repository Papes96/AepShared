package com.example.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ServiceProvider {
    fun getRetrofit() : IService {
        return Retrofit.Builder()
            .baseUrl("https://aepshared.rj.r.appspot.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(IService::class.java)
    }
}