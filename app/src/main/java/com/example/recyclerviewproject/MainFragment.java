package com.example.recyclerviewproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainFragment extends Fragment {
    private EditText textTodo;
    private RecyclerView list;

    private MyAdapter mAdapter;
    private ArrayList<Todo> dataSet;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance(String param1, String param2) {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        textTodo = view.findViewById(R.id.todo_input);
        list = view.findViewById(R.id.todo_list);
        RecyclerView.LayoutManager mLayourManager = new LinearLayoutManager(getContext());
        list.setLayoutManager(mLayourManager);

        dataSet = prepareDummyData();

        mAdapter = new MyAdapter(dataSet);
        list.setAdapter(mAdapter);
        Button addTodoButton = view.findViewById(R.id.button_add_todo);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataSet.add(new Todo(textTodo.getText().toString()));
                mAdapter.notifyDataSetChanged();
                textTodo.setText("");
            }
        });

        return view;
    }


    private ArrayList<Todo> prepareDummyData() {
        ArrayList<Todo> dataSet = new ArrayList<>();
        dataSet.add(new Todo("Todo 1"));
        dataSet.add(new Todo("Todo 2"));
        dataSet.add(new Todo("Todo 3"));
        dataSet.add(new Todo("Todo 4"));
        return dataSet;
    }
}