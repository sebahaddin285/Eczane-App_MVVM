package com.marangoz.eczane.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.marangoz.eczane.model.District
import com.marangoz.eczane.model.Province

@Database(entities = [Province::class, District::class], version = 1)
abstract class EczaneDataBase : RoomDatabase() {
    abstract fun getEczaneDao(): EczaneDao

    companion object {
        @Volatile
        var INSTANCE: EczaneDataBase? = null

        fun accsessDatabase(context: Context): EczaneDataBase? {
            if (INSTANCE == null) {
                synchronized(EczaneDataBase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        EczaneDataBase::class.java, "eczane.sqlite")
                        .createFromAsset("eczane.sqlite")
                        .build()
                }
            }
            return INSTANCE
        }
    }


}