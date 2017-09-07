package com.example.root.androidworkshop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.Iterator;
import java.util.Vector;

public class DoneToDo extends AppCompatActivity {

    ListView listView_DoneToDo;
    SQLiteManager db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_done_to_do);

        //Link gui component to object
        listView_DoneToDo = (ListView)findViewById(R.id.listView_DoneToDo);
        db = new SQLiteManager(getApplicationContext());

        //get the Done to do list from SQLite
        Vector<ToDo> vec = db.getDoneToDo();
        ToDo[] doneToDo = new ToDo[vec.size()];

        //convert from vector to object array
        int counter = 0;
        Iterator i = vec.iterator();
        while(i.hasNext()){
            doneToDo[counter] = (ToDo) i.next();
            counter++;
        }

        //Set the listview to the done to do list
        ListAdapter adapter = new ListViewFragment(this, doneToDo);
        listView_DoneToDo.setAdapter(adapter);
    }
}
