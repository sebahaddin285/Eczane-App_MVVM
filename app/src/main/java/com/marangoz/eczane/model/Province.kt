package com.marangoz.eczane.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "iller")
data class Province(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") @NotNull val id: Int,
    @ColumnInfo(name = "sehiradi") @NotNull val sehiradi: String


)
