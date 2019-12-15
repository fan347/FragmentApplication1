package com.example.fragmentapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final String CREAT_WORD="create table table_words ("
            +"id integer primary key autoincrement,"
            +"word text,"
            +"meaning text,"
            +"instance text)";
    private  static  final  String DB_NAME="words.db";
    private  static final int DB_VERSION=1;
    public DatabaseHelper(Context context){
        super(context,DB_NAME,null,DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREAT_WORD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
