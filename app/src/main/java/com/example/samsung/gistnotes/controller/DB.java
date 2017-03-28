package com.example.samsung.gistnotes.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.samsung.gistnotes.controller.exception.APICallException;
import com.example.samsung.gistnotes.model.Gists;
import com.example.samsung.gistnotes.model.GithubPublic;

import java.io.File;

/**
 * Created by btow on 27.03.2017.
 */

public class DB {

    private DBHelper dbHelper;
    private SQLiteDatabase database;
    private Cursor cursor;

    public DB(Context context, Gists gists) {
        this.dbHelper = new DBHelper(context);
        this.database = dbHelper.getWritableDatabase();

        try {
            loadGistsInBD(gists);
        } catch (APICallException e) {
            e.printStackTrace();
            Toast.makeText(context,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }

    private void loadGistsInBD(Gists gists) throws APICallException {

        if (gists != null) {

            cursor = getAllGists();
            if (cursor == null) {

                cursor.close();
                for (GithubPublic gist :
                        gists.getGithubPublicList()) {

                    addNewRowInGists(gist);
                }
            } else {

                for (GithubPublic gist :
                        gists.getGithubPublicList()) {

                    cursor.close();
                        cursor.close();
                        updateRowInGists(gist);
                }
            }
        } else {
            throw new APICallException();
        }

    }

    public void addNewRowInGists(GithubPublic githubPublic) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.KEY_GISTS_ID, githubPublic.getId());
        contentValues.put(DBHelper.KEY_GISTS_DESCRIPTION, githubPublic.getDescription());
        contentValues.put(DBHelper.KEY_GISTS_HAS_NOTE, false);
        contentValues.put(DBHelper.KEY_GISTS_USER, githubPublic.getUser() == null ? "null" : githubPublic.getUser().toString());
        database.insert(DBHelper.TABLE_GIT_HUB_GISTS, null, contentValues);
    }

    public void addNewRowInNotes (GithubPublic gist) {

        ContentValues contentValuesNote = new ContentValues(),
                      contentValuesGists = new ContentValues();
        contentValuesNote.put(DBHelper.KEY_NOTES_DESCRIPTION, gist.getDescription());
        contentValuesNote.put(DBHelper.KEY_NOTES_GISTS_ID, gist.getId());
        contentValuesGists.put(DBHelper.KEY_GISTS_HAS_NOTE, true);

        database.beginTransaction();
        try {
            database.insert(DBHelper.TABLE_GIT_HUB_NOTES, null, contentValuesNote);
            database.update(DBHelper.TABLE_GIT_HUB_GISTS, contentValuesGists,
                    DBHelper.KEY_GISTS_ID + " = ?",
                    new String[]{gist.getId()});
            database.setTransactionSuccessful();
        } finally {
            database.endTransaction();
        }
    }

    private void updateRowInGists(GithubPublic gist) {

        ContentValues contentValuesGists = new ContentValues();
        contentValuesGists.put(DBHelper.KEY_GISTS_HAS_NOTE, true);
        database.update(DBHelper.TABLE_GIT_HUB_GISTS, contentValuesGists,
                DBHelper.KEY_GISTS_ID + " = ?",
                new String[]{gist.getId()});
    }

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public Cursor getAllGists() {
        return database.query(DBHelper.TABLE_GIT_HUB_GISTS, null, null, null, null, null, null);
    }

    public Cursor getGistsWithFilter(GithubPublic gist) {
        String[] filter = {gist.getId()};
        return database.query(DBHelper.TABLE_GIT_HUB_GISTS, null, "_id = ?", filter, null, null, null);
    }

    public Cursor getNotes () {
        return database.query(DBHelper.TABLE_GIT_HUB_NOTES, null, null, null, null, null, null);
    }

    public Cursor getNoteOfGists (String[] gistsID) {

        return database.query(DBHelper.TABLE_GIT_HUB_NOTES, null, "gists_id = ?", gistsID, null, null, null);
    }
}
