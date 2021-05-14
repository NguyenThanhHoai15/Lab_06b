package com.example.projectabouteplace;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Place_db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME="tbl_Place";
    private static final String COLUMN_NAME = "name";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query;
        query = "CREATE TABLE " + TABLE_NAME
                + "(ID INTEGER PRIMARY KEY, "
                + COLUMN_NAME + " TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    //add the new place
    public void addPlace(String placeName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, placeName);

        //insert new row
        sqLiteDatabase.insert(TABLE_NAME, null, values);
        //close database connection
        sqLiteDatabase.close();
    }

    //get the all place
    public List<Place> getALLPlace(){
        List<Place> arrayList = new ArrayList<>();

        //select all query
        String select_query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(select_query, null);

        //looping through all rows and adding to list
        if(cursor.moveToFirst())
        {
            do{
                Place place = new Place();
                place.setID(cursor.getString(0));
                place.setName(cursor.getString(1));
                arrayList.add(place);
            }while (cursor.moveToNext());
        }
        return arrayList;
    }

    //delete place
    public void deletePlace(String ID){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //deleting row
        sqLiteDatabase.delete(TABLE_NAME, "ID = " + ID, null);
        sqLiteDatabase.close();
    }

    //update place
    public void updatePlace(String ID, String placeName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, placeName);
        //update Row
        sqLiteDatabase.update(TABLE_NAME, values, "ID = " + ID, null);
        sqLiteDatabase.close();
    }

}
