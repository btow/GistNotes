package com.example.samsung.gistnotes.controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TabHost;
import android.widget.Toast;

import com.example.samsung.gistnotes.R;
import com.example.samsung.gistnotes.api.GitApiInterface;
import com.example.samsung.gistnotes.api.GitHubClient;
import com.example.samsung.gistnotes.model.Gists;
import com.example.samsung.gistnotes.model.GithubPublic;

import java.io.BufferedReader;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllInOneActivity extends AppCompatActivity {

    private TabHost thAllInOne;

    DB db;

    Gists gists;
    private static final String LOG_TAG = "AllGistsActivity";

    private RecyclerView rvAllGists, rvAllNotes;
    private GistsAdapter gistsAdapter;
    private RecyclerView.LayoutManager layoutManagerGists, layoutManagerNotes;
    private GitApiInterface gitApiInterface;
    public Callback<Gists> gistsCallback;
    public Call<Gists> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_in_one);

        thAllInOne = (TabHost) findViewById(R.id.thAllInOne);
        thAllInOne.setup();
        TabHost.TabSpec tabSpec = thAllInOne.newTabSpec(getResources().getString(R.string.tag_all_gists));
        tabSpec.setContent(R.id.llAllGists);
        tabSpec.setIndicator(getResources().getString(R.string.all_gists));
        thAllInOne.addTab(tabSpec);

        tabSpec = thAllInOne.newTabSpec(getResources().getString(R.string.tag_notes));
        tabSpec.setContent(R.id.llNotes);
        tabSpec.setIndicator(getResources().getString(R.string.notes));
        thAllInOne.addTab(tabSpec);

        Intent intent = getIntent();
        thAllInOne.setCurrentTabByTag(intent.getStringExtra("defaultTab"));
        //We have the RecyclerView reference
        rvAllGists = (RecyclerView) findViewById(R.id.rvAllGists);
        rvAllNotes = (RecyclerView) findViewById(R.id.rvAllNotes);

        gitApiInterface = GitHubClient.getRetrofitClientWithOkHttpClient().create(GitApiInterface.class);
        call = gitApiInterface.getPublic();

        gistsCallback = new Callback<Gists>() {
            @Override
            public void onResponse(Call<Gists> call, Response<Gists> response) {
                Gists gists = new Gists();
                if (response.isSuccessful()) {
                    // request successful (status code 200, 201)
                    Toast.makeText(AllInOneActivity.this, "The data in format JSON is complied :)", Toast.LENGTH_SHORT);
                    gists.setGithubPublicList(response.body().getGithubPublicList());

                } else {
                    //request not successful (like 400,401,403 etc)
                    //Handle errors
                    Toast.makeText(AllInOneActivity.this, "Error of response " + response.code(), Toast.LENGTH_SHORT).show();
                    gists.setGithubPublicList(GithubPublic.readGists(getBaseContext()));
                }
                Log.d(LOG_TAG, "onResponse " + response.code());
                gistsAdapter = new GistsAdapter(gists);
                //Attach adapter to RecyclerViev
                layoutManagerGists = new LinearLayoutManager(getApplicationContext());
                rvAllGists.setLayoutManager(layoutManagerGists);
                rvAllGists.setAdapter(gistsAdapter);
                db = new DB(AllInOneActivity.this, gists);
            }

            @Override
            public void onFailure(Call<Gists> call, Throwable t) {
                Toast.makeText(AllInOneActivity.this, "Error :(", Toast.LENGTH_SHORT).show();
            }
        };
        call.enqueue(gistsCallback);

    }

}
