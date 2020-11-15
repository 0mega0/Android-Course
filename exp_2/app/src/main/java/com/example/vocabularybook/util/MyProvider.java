package com.example.vocabularybook.util;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyProvider extends ContentProvider {
    private static UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
    private MyDBOpenHelper dbOpenHelper;
    private static final String AUTHORITY = "com.example.notebook.provider";
    private String TABLE_NAME = "note";

    static {
        matcher.addURI("com.example.notebook.provider", "note", 1);
    }

//    private static final

    @Override
    public boolean onCreate() {
        dbOpenHelper = new MyDBOpenHelper(this.getContext(), "my.db", null, 1);
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        switch (matcher.match(uri)) {
            case 1:
                SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
                Cursor cursor = db.query("note", projection, selection, selectionArgs, null, null, sortOrder);
                return cursor;
        }
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.item/vnd/com.example.notebook.provider.note";
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        switch (matcher.match(uri)) {
            case 1:
                SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
                long rowId = db.insert("note", null, values);
                if (rowId > 0) {
                    return Uri.parse("content://" + AUTHORITY + "/note/" + rowId);
                }
        }
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
        int row = db.delete("note", selection, selectionArgs);
        return row;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
        int row = db.update("note", values, selection, selectionArgs);
        return row;
    }
}
