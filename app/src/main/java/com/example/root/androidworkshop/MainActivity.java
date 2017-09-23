package com.example.root.androidworkshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private SQLiteManager db;
    private ListView listView_todo;
    private ArrayList<Integer> ids;
    private Button button_tasksCompleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //link floating action button gui to object
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        //Set event handler for the floating button when clicked
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO: Checkpoint
                //startActivity(new Intent(MainActivity.this, InputTodo.class));
                startActivityForResult(new Intent(MainActivity.this, InputTodo.class), 1);
            }
        });

        //findViewById for listView and button


        //complete tasks button intent


        //get to do list from the SQLite database
        db = new SQLiteManager(getApplicationContext());
        Pair<ArrayList<Integer>, ArrayList<ToDo>> todolist = db.getToDo();
        ids = todolist.getLeft();
        final ArrayList<ToDo> listArray = todolist.getRight();

        //convert ArrayList to Array


        //fill in the Array with data


        //Fill in adapter with Array data


        //ListView set adapter


        //Event handler when the list within the list view are clicked



    }

    //Code that must be added for startActivityForResult
    /*
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
    */
}
