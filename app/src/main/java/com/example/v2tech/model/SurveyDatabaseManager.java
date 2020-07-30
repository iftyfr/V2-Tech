package com.example.v2tech.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SurveyDatabaseManager {

    DatabaseHelper dbHelper;
    Context context;

    public SurveyDatabaseManager(Context context) {
        this.context = context;
        dbHelper= new DatabaseHelper(context);
    }

    public long insertSurveyData(String data){
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.COLUMN_SURVEY_DATA, data);

        long insert =sqLiteDatabase.insert(DatabaseHelper.SURVEY_TABLE,null,contentValues);
        sqLiteDatabase.close();
        return insert;
    }


    public ArrayList<String> getAllSurvey(){
        ArrayList<String>surveyList=new ArrayList<>();
        String selectQuery = "select * from "+DatabaseHelper.SURVEY_TABLE;
        SQLiteDatabase sqLiteDatabase = dbHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery,null);

        if (cursor.moveToFirst()){
            do {
                int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID));
                String survey = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SURVEY_DATA));
                surveyList.add(survey);
            }while (cursor.moveToNext());
        }
        return surveyList;
    }
}
