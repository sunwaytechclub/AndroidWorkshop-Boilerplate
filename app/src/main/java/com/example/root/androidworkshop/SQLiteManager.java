package com.example.root.androidworkshop;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

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
    private static final String T_COL3 = "done" ;

    private static final String TABLE_CREATE = "CREATE TABLE "
            + TABLE_NAME + "( " + T_COL1
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + T_COL2
            + " TEXT NOT NULL, " + T_COL3
            + " INTEGER NOT NULL)" ;

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
        contentValues.put(T_COL3, 0);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        return !(result == -1);
    }

    public boolean updateStatus(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(T_COL3, 1);
        int result = db.update(TABLE_NAME, contentValues, T_COL1 + " = ?", new String[]{String.valueOf(id)});
        return !(result == 0);
    }

    public Pair<ArrayList<Integer>, ArrayList<ToDo>> getToDo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME
                + " where " + T_COL3 + " = '0' "
                , null);
        ArrayList<Integer> id = new ArrayList<>();
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
        //Vector<ToDo> vec = new Vector<>();
        while (cursor.moveToNext()) {
            id.add(cursor.getInt(0));
            toDoList.add(new ToDo(cursor.getString(1)));
            //vec.add(new ToDo(cursor.getString(1)));
        }
        cursor.close();
        db.close();
        return new Pair<ArrayList<Integer>, ArrayList<ToDo>>(id, toDoList);
    }

    public ArrayList<ToDo> getDoneToDo(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "select * from " + TABLE_NAME
                + " where " + T_COL3 + " = '1' "
                , null);
        //Vector<ToDo> vec = new Vector<>();
        ArrayList<ToDo> toDoList = new ArrayList<ToDo>();
        while(cursor.moveToNext()){
            toDoList.add(new ToDo(cursor.getString(1)));
        }
        cursor.close();
        db.close();
        return toDoList;
    }
}
