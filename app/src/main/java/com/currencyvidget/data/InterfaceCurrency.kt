package com.currencyvidget.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface InterfaceCurrency {

    @GET("/latest")
    suspend fun getCurrency() : Response<CurrencyData>


    @GET("/latest")
    suspend fun getSortedCurrency(@Query("base") baseId: String): Response<CurrencyData>
}