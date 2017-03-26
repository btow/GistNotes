package com.example.samsung.gistnotes.controller;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by btow on 26.03.2017.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "gist_notes",
        TABLE_GIT_HUB_NOTES         = "notes",
            KEY_NOTES_ID              = "_id",
            KEY_NOTES_GISTS_ID        = "gists_id",
            KEY_NOTES_DESCRIPTION     = "notes_descr",
            KEY_NOTES_NOTE            = "note";

    public DBHelper(Context context, String name, int version) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createBD = "create table " + TABLE_GIT_HUB_NOTES + "(" +
                KEY_NOTES_ID + " integer primary key autoincrement, " +
                KEY_NOTES_GISTS_ID + "text, " +
                KEY_NOTES_DESCRIPTION + "text, " +
                KEY_NOTES_NOTE + "text)";
        db.execSQL(createBD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
