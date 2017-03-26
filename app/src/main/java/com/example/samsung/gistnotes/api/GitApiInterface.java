package com.example.samsung.gistnotes.api;

import com.example.samsung.gistnotes.model.Gists;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by btow on 25.03.2017.
 */

public interface GitApiInterface {

    @GET("/gists/public")
    Call<Gists> getPublic();

}
