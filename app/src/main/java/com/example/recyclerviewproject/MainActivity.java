package com.example.recyclerviewproject;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private EditText todoText;
    private Button addButton;
    private RecyclerView todoList;

    private ArrayList<Todo>data;
    private MyAdapter mAdapter;

    private DbAdapter mDBAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoText = findViewById(R.id.todo_input);
        addButton = findViewById(R.id.button_add_todo);
        todoList = findViewById(R.id.todo_list);

       // prepareDummyData();

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
                    mDBAdapter.createTodo(todo); /*Quan afegim els todos s'han de desar a la base
                    de dades per poder restaurar-los després*/
                    data.add(todo);
                    mAdapter.notifyDataSetChanged();
                    todoText.setText("");
                }
            }
        });

        mDBAdapter = DbAdapter.getInstance(getApplicationContext()); /*Volem que la bbdd estigui
        disponible en tot moment però només per la nostra aplicació, per això passem el context*/
        mDBAdapter.open();
        if(mDBAdapter.isEmpty()){
            mDBAdapter.createTodo(new Todo("Todo 1"));
            mDBAdapter.createTodo(new Todo ("Todo 2"));
            mDBAdapter.createTodo(new Todo ("Todo 3"));
            mDBAdapter.createTodo(new Todo ("Todo 4"));
        }

        fetchData();
    }

    private void fetchData(){
        Cursor todoData = mDBAdapter.fetchAllTodos();
        this.data = new ArrayList<>();

        for(todoData.moveToFirst(); !todoData.isAfterLast(); todoData.moveToNext()){
            int id = todoData.getInt(0);
            String task = todoData.getString(1);

            this.data.add(new Todo(id, task));
        }
        mAdapter = new MyAdapter(data);
        todoList.setAdapter(mAdapter);
    }
   /* private void prepareDummyData(){
        data = new ArrayList<>();
        data.add(new Todo("Todo 1"));
        data.add(new Todo("Todo 2"));
        data.add(new Todo("Todo 3"));
        data.add(new Todo("Todo 4"));
    }*/
}
