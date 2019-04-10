package com.example.recyclerviewproject;

import java.util.Random;

class Todo {
    private int id;
    private String task;

    public Todo(String task){
        this.task = task;
        this.id = new Random().nextInt(99999);
    }

    public int getId() {
        return id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
