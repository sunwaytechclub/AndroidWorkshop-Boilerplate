package com.example.root.androidworkshop;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

public class InputTodo extends AppCompatActivity {

    EditText editText_Name;
    Button button_Add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_todo);

        //To set reference to the gui component
        editText_Name = (EditText)findViewById(R.id.editText_Name);
        button_Add = (Button)findViewById(R.id.button_Add);

        //Event handler for button_Add when click
        button_Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Instantiate SQLite database object
                SQLiteManager db = new SQLiteManager(getApplicationContext());

                //get back arrow to parent activity
                getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);

                //if its empty dont proceed
                if(TextUtils.isEmpty(editText_Name.getText())){
                    Toast.makeText(getApplicationContext(),"Please fill in the work to do",Toast.LENGTH_SHORT).show();
                    return ;
                }
                //add object into SQLite database
                boolean success = db.addToDo(new ToDo(editText_Name.getText().toString()));
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
