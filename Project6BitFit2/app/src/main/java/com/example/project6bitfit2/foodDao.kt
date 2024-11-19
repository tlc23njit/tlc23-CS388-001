package com.example.project6bitfit2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface foodDao {
    @Query("SELECT * FROM food_table")
    fun getAll(): Flow<List<food>>
    @Insert
    fun insertAll(waters: MutableList<foodEntity>)
    @Query("DELETE FROM food_table")
    fun deleteAll()
    @Insert
    fun insert(water: foodEntity)
    @Query("SELECT MIN(calAmount) FROM food_table")
    fun getMin(): Flow<Int>
    @Query("SELECT MAX(calAmount) FROM food_table")
    fun getMax(): Flow<Int>
    @Query("SELECT AVG(calAmount) FROM food_table")
    fun getAve(): Flow<Double>
}