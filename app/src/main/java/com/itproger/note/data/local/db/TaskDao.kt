package com.itproger.note.data.local.db

import androidx.room.*
import com.itproger.note.model.Task

@Dao
interface TaskDao {

    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(position: Task)

    @Delete
    fun delete(position: Task)
}