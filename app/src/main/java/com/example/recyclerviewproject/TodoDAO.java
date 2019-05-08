package com.example.recyclerviewproject;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDAO {

    @Query("SELECT * FROM todo")
    LiveData<List<Todo>> getAll();

    //Estràtegies configurables per evitar certs problemes en runtime
    @Insert(onConflict = OnConflictStrategy.REPLACE) /*En cas de voler fer un insert d'alguna cosa
    ja existent, fem un replace*/
    void insertTodo(Todo element);
}
