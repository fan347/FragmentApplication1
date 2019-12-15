package com.example.fragmentapplication;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fragmentapplication.*;

/**
 * 内容跟提供器，供外界查询dict表
 */

public class WordProvider extends ContentProvider {

    //这里的AUTHORITY就是我们在AndroidManifest.xml中配置的authorities，这里的authorities可以随便写
    private static final String AUTHORITY = "com.example.fragmentapplication.wordprovider";
    private String TABLE_NAME="table_words";
    private Context mContext;
    SQLiteDatabase db;
    //匹配成功后的匹配码
    private static final int MATCH_ALL_CODE = 1;
    private static final int MATCH_ONE_CODE = 2;
    private static final Uri NOTIFY_URI = Uri.parse("content://" + AUTHORITY + "/table_words");

    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, "table_words", MATCH_ALL_CODE);// 匹配表
        uriMatcher.addURI(AUTHORITY, "table_words/#", MATCH_ONE_CODE);// 匹配单条记录
    }

    @Override
    public boolean onCreate() {
        mContext = getContext();
        DatabaseHelper databaseHelper = new DatabaseHelper(mContext);
        db = databaseHelper.getWritableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        Cursor cursor = null;
        switch (uriMatcher.match(uri)) {
            case MATCH_ALL_CODE:
                cursor = db.query(TABLE_NAME,projection,selection,selectionArgs,null,null,sortOrder);
            case MATCH_ONE_CODE:
                String word = uri.getPathSegments().get(1);
                cursor = db.query(TABLE_NAME,projection,"word = ?",new String[]{word},null,null,sortOrder);
                break;
            default:
        }
        return cursor;
    }
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (uriMatcher.match(uri)){
            case MATCH_ALL_CODE:
                return "vnd.android.cursor.dir/vnd.com.example.fragmentapplication.wordprovider.table_words";
            case MATCH_ONE_CODE:
                return "vnd.android.cursor.dir/vnd.com.example.fragmentapplication.wordprovider.table_words";
        }
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (uriMatcher.match(uri)) {
            case MATCH_ALL_CODE:
                long id = db.insert(TABLE_NAME,null,values);
                return ContentUris.withAppendedId(uri,id);
            case MATCH_ONE_CODE:
                break;
            default:
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = 0;
        switch (uriMatcher.match(uri)) {
            case MATCH_ALL_CODE:
                Log.d("mmm", "deleteAll");
                id =db.delete(TABLE_NAME,selection,selectionArgs);
                break;
            case MATCH_ONE_CODE:
                String word = uri.getPathSegments().get(1);
                id = db.delete(TABLE_NAME,"id = ?",new String[] {word});
                break;
            default:
        }
        return id;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        int id = 0;
        switch (uriMatcher.match(uri)) {
            case MATCH_ALL_CODE:
                id = db.update(TABLE_NAME,values,selection,selectionArgs);
                break;
            case MATCH_ONE_CODE:
                String word = uri.getPathSegments().get(1);
                id = db.update(TABLE_NAME,values,"id = ?",new String[]{word});
                break;
            default:
        }
        return id;
    }
}