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

        //get the Done to do list from SQLite
        ArrayList<ToDo> listArray = db.getDoneToDo();


        final String[] toDoArray = new String[listArray.size()];
        for(int i=0;i<listArray.size();i++)
        {
            toDoArray[i]=listArray.get(i).getName();
        }

        //Set the listview to the done to do list
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,toDoArray);
        listView_DoneToDo.setAdapter(listAdapter);
    }
}
