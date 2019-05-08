package com.example.recyclerviewproject;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {
    private TodoDAO mTodoDao;
    private LiveData<List<Todo>> mAllTodos;

    public TodoRepository(Application application) {
        AppDatabase appDB = AppDatabase
                .getDatabase(application.getApplicationContext());

        mTodoDao = appDB.todoDAO();
        mAllTodos = mTodoDao.getAll();
    }

    public LiveData<List<Todo>> fetchAll() {
        return mAllTodos;
    }

    public void setTodo(String task) {
        Todo newTodo = new Todo();
        newTodo.task = task;
        new InsertAsyncTask(mTodoDao).execute(newTodo);
    }

    private static class InsertAsyncTask extends AsyncTask<Todo, Void, Void> {
        private TodoDAO mAsyncTaskDao;

        InsertAsyncTask(TodoDAO taskDao){
            mAsyncTaskDao = taskDao;
        }

        @Override
        protected Void doInBackground(Todo... todos) {
            mAsyncTaskDao.insertTodo(todos[0]);
            return null;
        }
    }
}

