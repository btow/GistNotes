package com.example.samsung.gistnotes.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.samsung.gistnotes.R;

public class NotesActivity extends AppCompatActivity {

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);
    }
}
