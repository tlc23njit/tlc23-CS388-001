package com.example.project6bitfit2

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "food_table")
data class foodEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "calAmount") val calAmount: String?,
    @ColumnInfo(name = "name") val name: String?,
)