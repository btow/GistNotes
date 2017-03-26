package com.example.samsung.gistnotes.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.samsung.gistnotes.R;
import com.example.samsung.gistnotes.api.GitApiInterface;
import com.example.samsung.gistnotes.api.GitHubClient;
import com.example.samsung.gistnotes.model.Gists;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by btow on 26.03.2017.
 */

public class AllGistsActivity extends AppCompatActivity implements View.OnClickListener {

    Gists gists;
    private static final String LOG_TAG = "AllGistsActivity";

    private RecyclerView rvAllGists;
    private Button btnNotes, btnAllInOne;

    private GistsAdapter gistsAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private GitApiInterface gitApiInterface;
    public Callback<Gists> gistsCallback;
    public Call<Gists> call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_gists);

        rvAllGists = (RecyclerView) findViewById(R.id.rvAllGists);
        btnNotes = (Button) findViewById(R.id.btnNotes);
        btnNotes.setOnClickListener(this);
        btnAllInOne = (Button) findViewById(R.id.btnAllInOne);
        btnAllInOne.setOnClickListener(this);

        gitApiInterface = GitHubClient.getRetrofitClient().create(GitApiInterface.class);
        call = gitApiInterface.getPublic();

        gistsCallback = new Callback<Gists>() {
            @Override
            public void onResponse(Call<Gists> call, Response<Gists> response) {
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    Toast.makeText(AllGistsActivity.this, "The data is complied :)", Toast.LENGTH_SHORT);

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(AllGistsActivity.this, "Error " + response.code(), Toast.LENGTH_SHORT).show();
                }
                Log.d(LOG_TAG, "onResponse " + response.code());
            }

            @Override
            public void onFailure(Call<Gists> call, Throwable t) {
                Toast.makeText(AllGistsActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        };

        call.enqueue(gistsCallback);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnNotes :
                Intent intentNotes = new Intent(this, NotesActivity.class);
                startActivity(intentNotes);
                break;
            case R.id.btnAllInOne :
                Intent intentAllInOne = new Intent(this, NotesActivity.class);
                startActivity(intentAllInOne);
                break;
        }

    }
}