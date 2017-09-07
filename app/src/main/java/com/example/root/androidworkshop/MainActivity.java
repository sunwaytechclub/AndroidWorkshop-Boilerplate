package com.example.root.androidworkshop;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SQLiteManager db;
    ListView listView_todo;
    ArrayList<Integer> ids;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        //Drawer layout code, auto generated when you create new navigation drawer activity
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        //Drawer layout code, auto generated when you create new navigation drawer activity
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //set reference from gui to the object
        listView_todo = (ListView)findViewById(R.id.listView_todo);

        //get to do list from the SQLite database
        db = new SQLiteManager(getApplicationContext());
        Pair<ArrayList<Integer>, Vector<ToDo>> todolist = db.getToDo();
        ids = todolist.getLeft();
        Vector<ToDo> v = todolist.getRight();

        final ToDo[] todo = new ToDo[v.size()];

        //convert vector data type to object array
        int counter = 0;
        Iterator i = v.iterator();
        while(i.hasNext()){
            todo[counter] = (ToDo)i.next();
            counter++;
        }

        //set the view of listView
        ListAdapter adapter = new ListViewFragment(this, todo);
        listView_todo.setAdapter(adapter);

        //Event handler when the list within the list view are clicked
        listView_todo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                //alert dialog will shown up
                new AlertDialog.Builder(MainActivity.this)
                        //title of alert dialog
                        .setTitle(todo[position].getName())
                        //message of alert dialog
                        .setMessage("Had you done " + todo[position].getName() + " ?")
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

    //When back button pressed, override the superclass method, by default is finish()
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    //add menu to the action bar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Code for when the option item are selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //When the item in navigation drawer are clicked
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_done) {
            startActivity(new Intent(MainActivity.this, DoneToDo.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
