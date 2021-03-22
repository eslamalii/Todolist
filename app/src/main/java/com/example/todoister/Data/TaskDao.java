package com.example.todoister.Data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoister.Model.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insert(Task task);

    @Query("DELETE FROM task_table ")
    void deleteAllTasks();

    @Query("SELECT * FROM task_table")
    LiveData<List<Task>> getTasks();

    @Query("SELECT * FROM task_table WHERE task_table.task_id == :id")
    LiveData<Task> get(long id);

    @Update
    void update(Task task);

    @Update
    void delete(Task task);
}
