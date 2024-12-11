package com.example.trivia

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "question_table")
data class QuestionEntity (
    @PrimaryKey (autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "q") val q: String,
    @ColumnInfo(name = "a") val a: String,
    @ColumnInfo(name = "point") val point: Int,
    @ColumnInfo(name = "cat") val cat: String,
    )
{
}