package com.example.samsung.gistnotes.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.samsung.gistnotes.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    public static final String LOG_TAG = "gistNotesMainActivity";
    private Button btnAllGists, btnNotes, btnAllInOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnAllGists = (Button) findViewById(R.id.btnAllGists);
        btnAllGists.setOnClickListener(this);
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(this);
        btnAllInOne = (Button) findViewById(R.id.btnAllInOne);
        btnAllInOne.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        Intent intentAllGists = new Intent(this, AllInOneActivity.class);

        switch (v.getId()) {

            case R.id.btnNotes :
                intentAllGists.putExtra("defaultTab", getString(R.string.tag_notes));
                break;
            default :
                intentAllGists.putExtra("defaultTab", getString(R.string.tag_all_gists));
                break;

        }

        startActivity(intentAllGists);
    }
}
