package com.example.fragmentapplication;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class WordDao {
    private SQLiteDatabase db;
    private String TABLE_NAME = "table_words";

    public WordDao(Context context) {
        DatabaseHelper dbH = new DatabaseHelper(context);
        db = dbH.getWritableDatabase();
    }

    public void add(Word word) {
        ContentValues values = new ContentValues();
        values.put("word", word.getWordThis());
        values.put("meaning", word.getWordMeaning());
        values.put("instance", word.getWordInstance());
        db.insert(TABLE_NAME, null, values);
        values.clear();
    }
    public void delete(Word word){
        db.delete(TABLE_NAME,"word=? ",new String[]{word.getWordThis()});
    }
    public void update(Word word1,Word word2){
        ContentValues values = new ContentValues();
        values.put("word", word2.getWordThis());
        values.put("meaning", word2.getWordMeaning());
        values.put("instance", word2.getWordInstance());
        db.update(TABLE_NAME,values,"word=? and instance=?",new String[]{word1.getWordThis(),word1.getWordInstance()});
    }
    public ArrayList<Word> findAll(){
        ArrayList<Word> wordArrayList=new ArrayList<>();
        Cursor cursor =db.query(TABLE_NAME,null,null,null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                String word=cursor.getString(cursor.getColumnIndex("word"));
                String meaning=cursor.getString(cursor.getColumnIndex("meaning"));
                String instance=cursor.getString(cursor.getColumnIndex("instance"));
                Word words=new Word(word,meaning,instance);
                wordArrayList.add(words);
            }while(cursor.moveToNext());
        }
        cursor.close();
       return wordArrayList;
    }
    public ArrayList<Word> findBlur(String str){
        String strBlur=null;
        for(int i=0;i<str.length();i++){
            strBlur="%"+str.charAt(i);
        }
        strBlur+="%";

        ArrayList<Word> wordArrayList=new ArrayList<>();
        Cursor cursor =db.query(TABLE_NAME,null,"word like ?",new String[]{ strBlur},null,null,null);
        if(cursor.moveToFirst()){
            do{
                String word=cursor.getString(cursor.getColumnIndex("word"));
                String meaning=cursor.getString(cursor.getColumnIndex("meaning"));
                String instance=cursor.getString(cursor.getColumnIndex("instance"));
                Word words=new Word(word,meaning,instance);
                wordArrayList.add(words);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return wordArrayList;
    }
}
