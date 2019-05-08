package com.example.recyclerviewproject;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mRepository;
    private LiveData<List<Todo>> mAllTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TodoRepository(application);
        mAllTodos = mRepository.fetchAll();
    }

    LiveData<List<Todo>> getmAllTodos(){
        return mAllTodos;
    }

    void insert(String task){
        mRepository.setTodo(task);
    }
}
