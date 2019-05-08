package com.example.recyclerviewproject;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Todo {
    @PrimaryKey(autoGenerate = true) //Id autoincrementat començant des de 1
    public int id;

    @ColumnInfo(name="task") //Nom del camp a la base de dades, en cas de ser el mateix no cal
    public String task;
}
