package com.example.root.androidworkshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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
                startActivityForResult(new Intent(
                        MainActivity.this, InputTodo.class
                ), 1);
            }
        });

        //ref listview
        listView_todo = (ListView)findViewById(R.id.listView_todo);

        //complete tasks button intent
        button_tasksCompleted= (Button)findViewById(R.id.button_completedTasks);
        button_tasksCompleted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,DoneToDo.class);
                startActivity(intent);
            }
        });

        //get to do list from the SQLite database
        db = new SQLiteManager(getApplicationContext());
        Pair<ArrayList<Integer>, ArrayList<ToDo>> todolist = db.getToDo();
        ids = todolist.getLeft();
        final ArrayList<ToDo> listArray = todolist.getRight();
        final String[] toDoArray = new String[listArray.size()];
        for(int i=0;i<listArray.size();i++)
        {
            toDoArray[i]=listArray.get(i).getName();
            Log.d("todo",toDoArray[i]);
        }
        //set the view of listView
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,toDoArray);

        listView_todo.setAdapter(listAdapter);

        //Event handler when the list within the list view are clicked
        listView_todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //alert dialog will shown up
                new AlertDialog.Builder(MainActivity.this)
                        //title of alert dialog
                        .setTitle(toDoArray[position])
                        //message of alert dialog
                        .setMessage("Have you done " + toDoArray[position] + " ?")
                        //positive button, the button at the position usually place for 'ok'
                        .setPositiveButton("Done",new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                boolean success = db.updateStatus(ids.get(position).intValue());
                                if(success){
                                    Toast.makeText(getApplicationContext(), "Successfully updated",
                                            Toast.LENGTH_LONG).show();
                                }
                                finish();
                                startActivity(getIntent());
                            }
                        })
                        //negative button, the button at the position usually place for 'cancel'
                        .setNegativeButton("Not Yet", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });
    }

    //Code that must be added for startActivityForResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Intent refresh = new Intent(this, MainActivity.class);
            startActivity(refresh);
            this.finish();
        }
    }
}
