package com.example.v2tech.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="SurveyDB";
    public static final int DATABASE_VERSION=1;

    public static final String SURVEY_TABLE="surveyTable";
    public static final String COLUMN_ID="id";
    public static final String COLUMN_SURVEY_DATA="surveyData";

    public static final String CREATE_SURVEY_TABLE="create table "+SURVEY_TABLE+"("+COLUMN_ID+" integer primary key autoincrement,"+COLUMN_SURVEY_DATA+" text);";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_SURVEY_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists "+SURVEY_TABLE);
        onCreate(sqLiteDatabase);
    }
}
