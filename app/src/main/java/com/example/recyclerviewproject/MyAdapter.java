package com.example.recyclerviewproject;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Todo> data = Collections.emptyList();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        //Creem la vista i ens preparem per omplirar-la
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item, viewGroup, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    public void setTodos(List<Todo> allTodos) {
        data = allTodos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        //Omplir vista
        Todo todo = data.get(i);

        viewHolder.mId.setText(todo.id + "");
        viewHolder.mTask.setText(todo.task);
    }

    @Override
    public int getItemCount() {
        //Retornar número d'items que tenim a cada moment (detectar canvis per refrescar UI)
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mId;
        public TextView mTask;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            mId = itemView.findViewById(R.id.todo_id);
            mTask = itemView.findViewById(R.id.todo_task);
        }
    }
}
