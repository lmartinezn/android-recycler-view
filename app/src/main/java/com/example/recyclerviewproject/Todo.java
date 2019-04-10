package com.example.recyclerviewproject;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Random;

class Todo implements Parcelable {
    private int id;
    private String task;

    public Todo(String task) {
        this.task = task;
        this.id = new Random().nextInt(99999);
    }

    public Todo(Parcel in){
        super();
        readFromParcel(in);
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(task);
    }

    public void readFromParcel(Parcel in) {
        id = in.readInt(); //Important respectar l'ordre per evitar possibles errors
        task = in.readString();
    }

    public static final Parcelable.Creator<Todo> CREATOR =
            new Parcelable.Creator<Todo>() {

                public Todo createFromParcel(Parcel in) {
                    return new Todo(in);
                }

                public Todo[] newArray(int size) {
                    return new Todo[size];
                }

            };
}