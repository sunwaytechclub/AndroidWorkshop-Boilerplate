package com.example.root.androidworkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by root on 9/6/17.
 */

public class SQLiteManager extends SQLiteOpenHelper {

    private static final String TAG = SQLiteManager.class.getSimpleName();

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "TodoApp" ;
    private static final String TABLE_NAME = "To_Do_List" ;
    private static final String T_COL1 = "id" ;
    private static final String T_COL2 = "name" ;
    private static final String T_COL3 = "date" ;
    private static final String T_COL4 = "done" ;

    private static final String TABLE_CREATE = "create table "
            + TABLE_NAME + "( " + T_COL1
            + " integer primary key autoincrement, " + T_COL2
            + " text not null, " + T_COL3
            + " text not null, " + T_COL4
            + " integer not null);";

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //TODO: To be implement
    }

    public boolean addToDo(ToDo todo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_COL2, todo.getName());
        contentValues.put(T_COL3, todo.getDate().toString());
        contentValues.put(T_COL4, 0);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return !(result == -1);
    }

    public boolean updateStatus(int id){
        //TODO: Update pending to done in database
        return true;
    }
}
