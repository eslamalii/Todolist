package com.example.todoister.Model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoister.Data.TaskRepo;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    public static TaskRepo taskRepo;
    public final LiveData<List<Task>> DATA;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        taskRepo = new TaskRepo(application);
        DATA = taskRepo.getTasks();
    }

    public LiveData<List<Task>> getDATA() {
        return DATA;
    }

    public static void insert(Task task) {
        taskRepo.insert(task);
    }

    public LiveData<Task> get(long id) {
        return taskRepo.get(id);
    }

    public static void update(Task task) {
        taskRepo.update(task);
    }

    public static void delete(Task task) {
        taskRepo.delete(task);
    }
}
