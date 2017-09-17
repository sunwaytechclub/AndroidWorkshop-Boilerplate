package com.example.root.androidworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;

public class DoneToDo extends AppCompatActivity {

    ListView listView_DoneToDo;
    SQLiteManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_to_do);

        //get back arrow to parent activity
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

        //Link gui component to object
        listView_DoneToDo = (ListView)findViewById(R.id.listView_DoneToDo);
        db = new SQLiteManager(getApplicationContext());

    }
}
