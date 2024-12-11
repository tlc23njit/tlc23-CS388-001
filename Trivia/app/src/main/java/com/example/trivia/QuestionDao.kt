package com.example.trivia

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface QuestionDao {
    @Query("SELECT * FROM question_table")
    fun getAll(): Flow<List<Question>>
    @Insert
    fun insertAll(qs: MutableList<QuestionEntity>)
    @Query("DELETE FROM question_table")
    fun deleteAll()
    @Insert
    fun insert(q: QuestionEntity)
}
