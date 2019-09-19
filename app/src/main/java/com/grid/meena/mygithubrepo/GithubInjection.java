package com.grid.meena.mygithubrepo;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

class GithubInjection {

    private static final Gson gson = new GsonBuilder()
            .setLenient()
            .create();

    private static Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build();

    private GithubInjection() {
        throw new AssertionError("GithubInjection object not allowed");
    }

    static GithubService provideGithubService() {
        return retrofit.create(GithubService.class);
    }
}
