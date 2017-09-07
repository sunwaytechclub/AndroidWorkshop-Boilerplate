package com.example.root.androidworkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Vector;

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

    //constructor
    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //for creating the table
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    //Can be ignore for now
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        //To be implement
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
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_COL4, 1);
        int result = db.update(TABLE_NAME, contentValues, T_COL1 + " = ?", new String[]{String.valueOf(id)});
        return !(result == 0);
    }

    public Pair<ArrayList<Integer>, Vector<ToDo>> getToDo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME
                + " where " + T_COL4 + " = '0' "
                + " order by " + T_COL3 + " ASC;"
                , null);
        ArrayList<Integer> id = new ArrayList<>();
        Vector<ToDo> vec = new Vector<>();
        while (cursor.moveToNext()) {
            id.add(cursor.getInt(0));
            vec.add(new ToDo(cursor.getString(1), new Date(cursor.getString(2))));
        }
        cursor.close();
        db.close();
        return new Pair<ArrayList<Integer>, Vector<ToDo>>(id, vec);
    }

    public Vector<ToDo> getDoneToDo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME
                + " where " + T_COL4 + " = '1' "
                + " order by " + T_COL3 + " ASC;"
                , null);
        Vector<ToDo> vec = new Vector<>();
        while(cursor.moveToNext()){
            vec.add(new ToDo(cursor.getString(1), new Date(cursor.getString(2))));
        }
        cursor.close();
        db.close();
        return vec;
    }
}
