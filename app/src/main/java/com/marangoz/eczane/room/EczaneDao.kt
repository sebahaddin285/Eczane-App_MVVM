package com.marangoz.eczane.room

import androidx.room.Dao
import androidx.room.Query
import com.marangoz.eczane.model.District
import com.marangoz.eczane.model.Province

@Dao
interface EczaneDao {

    @Query("select * from iller")
    suspend fun allProvince(): List<Province>

    @Query("select * from ilceler WHERE sehirid =:sehirid")
    suspend fun district(sehirid : Int) : List<District>

}