package com.example.todoister.Data;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.todoister.Model.Task;
import com.example.todoister.Util.TaskRoomDatabase;

import java.util.List;

public class TaskRepo {
    private final TaskDao taskDao;
    private final LiveData<List<Task>> tasks;

    public TaskRepo(Application application) {
        TaskRoomDatabase database = TaskRoomDatabase.getINSTANCE(application);

        taskDao = database.taskDao();
        tasks = taskDao.getTasks();
    }

    public LiveData<List<Task>> getTasks() {
        return tasks;
    }

    public void insert(Task task) {
        TaskRoomDatabase.EXECUTOR_SERVICE.execute(() -> taskDao.insert(task));
    }

    public LiveData<Task> get(long id) {
        return taskDao.get(id);
    }

    public void update(Task task) {
        TaskRoomDatabase.EXECUTOR_SERVICE.execute(() -> taskDao.update(task));
    }

    public void delete(Task task) {
        TaskRoomDatabase.EXECUTOR_SERVICE.execute(() -> taskDao.delete(task));
    }


}
