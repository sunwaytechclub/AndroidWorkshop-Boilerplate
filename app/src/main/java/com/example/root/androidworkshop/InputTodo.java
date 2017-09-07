package com.example.root.androidworkshop;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class InputTodo extends AppCompatActivity {

    EditText editText_Name;
    EditText editText_Date;
    Button button_Add;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_todo);

        //To set reference to the gui component
        editText_Name = (EditText)findViewById(R.id.editText_Name);
        editText_Date = (EditText)findViewById(R.id.editText_Date);
        button_Add = (Button)findViewById(R.id.button_Add);

        //To make the date Edit Text cannot be input text
        editText_Date.setFocusable(false);
        editText_Date.setClickable(true);

        //To initialize the date Edit Text field
        java.util.Calendar cal = java.util.Calendar.getInstance();
        int year = cal.get(java.util.Calendar.YEAR);
        int month = cal.get(java.util.Calendar.MONTH) + 1;
        int day = cal.get(java.util.Calendar.DAY_OF_MONTH);
        editText_Date.setText(year + "-" + month + "-" + day);

        //Event handler for when user click the editText_Date
        editText_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = editText_Date.getText().toString();
                String temp[] = date.split("-");

                DatePickerDialog datePicker = new DatePickerDialog(InputTodo.this,
                        dateSetListener,
                        Integer.parseInt(temp[0]), Integer.parseInt(temp[1]) - 1, Integer.parseInt(temp[2]));
                datePicker.show();
            }
        });

        //Set the action when the user pick date from the calender shown up
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                month++;
                String date = year + "-" + month + "-" + day;
                editText_Date.setText(date);
            }
        };

        //Event handler for button_Add when click
        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate SQLite database object
                SQLiteManager db = new SQLiteManager(getApplicationContext());

                //add object into SQLite database
                boolean success = db.addToDo(new ToDo(
                        editText_Name.getText().toString(),
                        Date.displayStringToDate(editText_Date.getText().toString())
                ));
                if (success){
                    //Toast is showing a message on screen that will gone within few second
                    Toast.makeText(getApplicationContext(), "Successfully Added",
                        Toast.LENGTH_LONG).show();
                }
                //update result for MainActivity
                setResult(RESULT_OK, null);
                //end current activity
                finish();
            }
        });
    }
}
