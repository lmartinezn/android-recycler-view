package com.example.recyclerviewproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText todoText;
    private Button addButton;
    private RecyclerView todoList;

    private MyAdapter mAdapter;
    private TodoViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        todoText = findViewById(R.id.todo_input);
        addButton = findViewById(R.id.button_add_todo);
        todoList = findViewById(R.id.todo_list);

       // RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this, 2);

        todoList.setLayoutManager(mLayoutManager);

        mViewModel = ViewModelProviders.of(this).get(TodoViewModel.class); /* El view model
        sobreviu als canvis d'orientació, no ens interessa crear un de nou cada cop sinó obtenir
        la instància existent si hi ha i només si no n'hi ha cap, crear-ne un*/
        mViewModel.getmAllTodos().observe(this, new Observer<List<Todo>>() {
            /*Listener per a un event concret (quan hi han hagut canvis, així detectarà quan s'ha
            d'actualitzar les dades que s'estan mostrant*/
            @Override
            public void onChanged(List<Todo> todos) {
                mAdapter.setTodos(todos);
            }
        });

        mAdapter = new MyAdapter();
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
                    mViewModel.insert(todoText.getText().toString());
                    todoText.setText("");
                }
            }
        });
    }
}
