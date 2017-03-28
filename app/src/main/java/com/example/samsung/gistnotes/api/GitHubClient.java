package com.example.samsung.gistnotes.api;

import com.example.samsung.gistnotes.model.GithubPublic;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by btow on 25.03.2017.
 */

public class GitHubClient {

    private static final String BASE_URL = "https://api.github.com/" ;
    private static Retrofit retrofitClient = null;
    private static OkHttpClient okHttpClient;

    public static Retrofit getRetrofitClientWithOkHttpClient() {

        okHttpClient = new Builder()
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Interceptor.Chain chain) throws IOException {
                        Request request = chain.request().newBuilder()
                                .addHeader("Accept", "Applocation/JSON")
                                .build();
                        return chain.proceed(request);
                    }
                }).build();

        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }

    public static Retrofit getRetrofitClient() {

        if (retrofitClient == null) {
            retrofitClient = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofitClient;
    }
}
