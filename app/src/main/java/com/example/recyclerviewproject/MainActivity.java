package com.example.recyclerviewproject;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        Fragment bf = fm.findFragmentById(R.id.list_fragment);
        if (bf == null) {
            FragmentTransaction ft = fm.beginTransaction();
            MainFragment mf = MainFragment.newInstance();
            ft.add(R.id.list_fragment, mf);
            ft.commit();
        }

    }
}
