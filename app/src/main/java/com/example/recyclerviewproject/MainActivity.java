package com.example.recyclerviewproject;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText todoText;
    private Button addButton;
    private RecyclerView todoList;

    private ArrayList<Todo>data;
    private MyAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoText = findViewById(R.id.todo_input);
        addButton = findViewById(R.id.button_add_todo);
        todoList = findViewById(R.id.todo_list);

        if(savedInstanceState != null){ //Si hi ha dades desades, les restaurem
            this.data = savedInstanceState.getParcelableArrayList("savedData");
        }
        else{ //Si no, omplim amb dades per defecte
            prepareDummyData(); //Desar les dades no servirà de res si sempre les sobreescrivim
        }

       // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        todoList.setLayoutManager(mLayoutManager);

        mAdapter = new MyAdapter(data);
        todoList.setAdapter(mAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = todoText.getText().toString();

                if(task.equals("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setMessage("Empty text!");
                    builder.create().show();
                }
                else{
                    Todo todo = new Todo (todoText.getText().toString());
                    data.add(todo);
                    mAdapter.notifyDataSetChanged();
                    todoText.setText("");
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState){
        outState.putParcelableArrayList("savedData", this.data);
        super.onSaveInstanceState(outState);
    }

    private void prepareDummyData(){
        data = new ArrayList<>();
        data.add(new Todo("Todo 1"));
        data.add(new Todo("Todo 2"));
        data.add(new Todo("Todo 3"));
        data.add(new Todo("Todo 4"));
    }
}
