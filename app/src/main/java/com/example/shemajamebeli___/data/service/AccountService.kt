package com.example.shemajamebeli___.data.service

import com.example.shemajamebeli___.data.model.FromAccountDto
import com.example.shemajamebeli___.data.model.ToAccountDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface AccountService {
    @GET("5c74ec0e-5cc1-445e-b64b-b76b286b215f")
    suspend fun getFromAccount(): Response<List<FromAccountDto>>

    @GET("4253786f-3500-4148-9ebc-1fe7fb9dc8d5")
    suspend fun getToAccount(@Query("account_number") accountNumber: String): Response<ToAccountDto>
}