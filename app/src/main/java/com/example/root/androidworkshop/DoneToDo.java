package com.example.root.androidworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class DoneToDo extends AppCompatActivity {

    ListView listView_DoneToDo;
    SQLiteManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_to_do);

        //get back arrow to parent activity
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        db = new SQLiteManager(getApplicationContext());


        //Link gui component to object

        //get list from SQLlite

        //convert arraylist to array

        
        //fill in array with data


        //Set arrayAdapter on the array

        //Set listView adapter


    }
}
