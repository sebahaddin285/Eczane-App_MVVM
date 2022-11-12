package com.marangoz.eczane.repository

import com.marangoz.eczane.model.District
import com.marangoz.eczane.model.Objetc
import com.marangoz.eczane.model.Province
import com.marangoz.eczane.model.Result
import com.marangoz.eczane.room.EczaneDao
import com.marangoz.eczane.utils.RetrofitInstance
import retrofit2.Response

class Repository(val eDao: EczaneDao) {

    suspend fun allProvince(): List<Province> {
        return eDao.allProvince()
    }

    suspend fun getDistrict(sehirId: Int): List<District> {
        return eDao.district(sehirId)
    }

    suspend fun getCustomEczane(ilce: String? = null, il: String): Response<Objetc>? {


        return RetrofitInstance.api.getCustomEczane(ilce, il)


    }

}