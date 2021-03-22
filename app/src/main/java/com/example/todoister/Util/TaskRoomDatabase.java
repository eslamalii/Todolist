package com.example.todoister.Util;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.todoister.Data.TaskDao;
import com.example.todoister.Model.Task;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
@TypeConverters({Converter.class})
public abstract class TaskRoomDatabase extends RoomDatabase {

    public static final int NUMBER_OF_THREADS = 4;
    private static volatile TaskRoomDatabase INSTANCE;
    public static final String DATABASE_NAME = "todolist_database";
    public static final ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static final RoomDatabase.Callback sRoomDatabase =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onCreate(db);

                    EXECUTOR_SERVICE.execute(() -> {
                        //invoke Dao
                        TaskDao taskDao = INSTANCE.taskDao();
                        taskDao.deleteAllTasks();
                    });

                }
            };

    public static TaskRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TaskRoomDatabase.class, DATABASE_NAME)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract TaskDao taskDao();

}
