package com.marangoz.eczane.api

import com.marangoz.eczane.model.Objetc
import com.marangoz.eczane.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {

    @GET("/health/dutyPharmacy")
    suspend fun getCustomEczane(
        @Query("ilce") ilce : String? = null,
        @Query("il") il : String
    ) : Response<Objetc>

}