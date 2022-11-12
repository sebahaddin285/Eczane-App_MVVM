package com.marangoz.eczane.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "ilceler")
data class District(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull val id : Int,
    @ColumnInfo(name = "ilceadi") @NotNull val ilceadi : String,
    @ColumnInfo(name = "sehirid") @NotNull val sehirid : Int
)
